package com.hiaashuu.appteka.screen.upload

import android.os.Build
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.categories.CategoryItem
import com.hiaashuu.appteka.screen.upload.adapter.category.SelectCategoryItem
import com.hiaashuu.appteka.screen.upload.adapter.description.DescriptionItem
import com.hiaashuu.appteka.screen.upload.adapter.exclusive.ExclusiveItem
import com.hiaashuu.appteka.screen.upload.adapter.notice.NoticeItem
import com.hiaashuu.appteka.screen.upload.adapter.notice.NoticeType
import com.hiaashuu.appteka.screen.upload.adapter.open_source.OpenSourceItem
import com.hiaashuu.appteka.screen.upload.adapter.other_versions.OtherVersionsItem
import com.hiaashuu.appteka.screen.upload.adapter.other_versions.VersionItem
import com.hiaashuu.appteka.screen.upload.adapter.prefill_version.PrefillVersionItem
import com.hiaashuu.appteka.screen.upload.adapter.screen_append.ScreenAppendItem
import com.hiaashuu.appteka.screen.upload.adapter.screen_image.ScreenImageItem
import com.hiaashuu.appteka.screen.upload.adapter.screenshots.ScreenshotsItem
import com.hiaashuu.appteka.screen.upload.adapter.select_app.SelectAppItem
import com.hiaashuu.appteka.screen.upload.adapter.selected_app.SelectedAppItem
import com.hiaashuu.appteka.screen.upload.adapter.submit.SubmitItem
import com.hiaashuu.appteka.screen.upload.adapter.whats_new.WhatsNewItem
import com.hiaashuu.appteka.screen.upload.api.CheckExistResponse
import com.hiaashuu.appteka.screen.upload.dto.UploadScreenshot
import com.hiaashuu.appteka.core.permissions.Capability
import com.hiaashuu.appteka.upload.UploadApk
import com.hiaashuu.appteka.upload.UploadPackage
import com.hiaashuu.appteka.util.versionCodeCompat

interface UploadConverter {

    fun convert(
        pkg: UploadPackage?,
        apk: UploadApk?,
        checkExist: CheckExistResponse?,
        screenshots: List<UploadScreenshot>,
        category: CategoryItem?,
        whatsNew: String,
        description: String,
        exclusive: Boolean,
        openSource: Boolean,
        sourceUrl: String,
        highlightErrors: Boolean,
        selectedPrefillVersion: VersionItem?,
        bypassModerationCapability: Capability?,
    ): List<Item>

}

class UploadConverterImpl(
    private val resourceProvider: UploadResourceProvider,
    private val descriptionValidator: DescriptionValidator
) : UploadConverter {

    override fun convert(
        pkg: UploadPackage?,
        apk: UploadApk?,
        checkExist: CheckExistResponse?,
        screenshots: List<UploadScreenshot>,
        category: CategoryItem?,
        whatsNew: String,
        description: String,
        exclusive: Boolean,
        openSource: Boolean,
        sourceUrl: String,
        highlightErrors: Boolean,
        selectedPrefillVersion: VersionItem?,
        bypassModerationCapability: Capability?,
    ): List<Item> {
        var id: Long = 1
        val items = ArrayList<Item>()
        val isEditMode = checkExist?.file?.appId != null

        if (apk != null) {
            items += SelectedAppItem(id++, apk)
        } else if (pkg == null) {
            items += SelectAppItem(id++)
        }

        if (apk != null) {
            resourceProvider.moderationNotice(bypassModerationCapability)?.let { text ->
                items += NoticeItem(id++, NoticeType.INFO, text, clickable = false)
            }
        }

        var isUploadAvailable = true
        var versions: List<VersionItem> = emptyList()

        checkExist?.let {
            val clickable = checkExist.file != null
            if (checkExist.info?.isEmpty() == false) {
                items += NoticeItem(id++, NoticeType.INFO, checkExist.info, clickable)
            }
            if (checkExist.warning?.isEmpty() == false) {
                items += NoticeItem(id++, NoticeType.WARNING, checkExist.warning, clickable)
            }
            if (checkExist.error?.isEmpty() == false) {
                items += NoticeItem(id++, NoticeType.ERROR, checkExist.error, clickable)
                isUploadAvailable = false
            }

            checkExist.versions
                ?.takeIf { it.isNotEmpty() }
                ?.run {
                    versions = this
                        .sortedBy { it.verCode }
                        .reversed()
                        .map { version ->
                            VersionItem(
                                versionId = version.appId.hashCode(),
                                appId = version.appId,
                                title = resourceProvider.formatVersion(version),
                                compatible = version.sdkVersion <= Build.VERSION.SDK_INT,
                                newer = apk?.packageInfo?.versionCodeCompat()
                                    ?.let { version.verCode > it } == true,
                            )
                        }
                    items += OtherVersionsItem(id++, versions)
                }
        }

        if (isUploadAvailable) {

            if (!isEditMode && versions.isNotEmpty()) {
                items += PrefillVersionItem(
                    id = id++,
                    versions = versions,
                    selectedVersion = selectedPrefillVersion
                )
            }

            items += ScreenshotsItem(
                id = id++,
                items = screenshots.map {
                    ScreenImageItem(
                        id = it.longId(),
                        original = it.original,
                        preview = it.preview,
                        width = it.width,
                        height = it.height,
                        remote = it.remote()
                    )
                } + ScreenAppendItem(id++)
            )
            items += SelectCategoryItem(
                id++,
                category = category,
                errorRequiredField = highlightErrors && category == null
            )
            items += WhatsNewItem(id++, text = whatsNew)
            val descriptionBlank = description.isBlank()
            val descriptionTooShort = !descriptionBlank && !descriptionValidator.isValid(description)
            items += DescriptionItem(
                id++,
                text = description,
                errorRequiredField = highlightErrors && descriptionBlank,
                errorMinLength = highlightErrors && descriptionTooShort
            )
            items += ExclusiveItem(id++, value = exclusive)
            items += OpenSourceItem(id++, value = openSource, url = sourceUrl)
            items += SubmitItem(id++, editMode = isEditMode, enabled = (isEditMode || apk != null))
        }

        return items
    }

    private fun UploadScreenshot.longId(): Long =
        (scrId?.hashCode() ?: original.toString().hashCode()).toLong()

    private fun UploadScreenshot.remote(): Boolean = scrId != null

}