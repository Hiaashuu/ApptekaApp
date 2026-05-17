package com.hiaashuu.appteka.screen.details

import android.net.Uri
import com.hiaashuu.appteka.core.permissions.CapabilityAction
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.categories.DEFAULT_LOCALE
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiItem
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiResourceProvider
import com.hiaashuu.appteka.screen.details.adapter.controls.ControlsItem
import com.hiaashuu.appteka.screen.details.adapter.description.DescriptionItem
import com.hiaashuu.appteka.screen.details.adapter.discuss.DiscussItem
import com.hiaashuu.appteka.screen.details.adapter.header.HeaderItem
import com.hiaashuu.appteka.screen.details.adapter.permissions.PermissionsItem
import com.hiaashuu.appteka.screen.details.adapter.play.PlayItem
import com.hiaashuu.appteka.screen.details.adapter.play.PlaySecurityStatus
import com.hiaashuu.appteka.screen.details.adapter.security.SecurityItem
import com.hiaashuu.appteka.screen.details.adapter.security.SecurityType
import com.hiaashuu.appteka.screen.details.adapter.rating.RatingItem
import com.hiaashuu.appteka.screen.details.adapter.scores.ScoresItem
import com.hiaashuu.appteka.screen.details.adapter.screenshot.ScreenshotItem
import com.hiaashuu.appteka.screen.details.adapter.screenshots.ScreenshotsItem
import com.hiaashuu.appteka.screen.details.adapter.status.StatusAction
import com.hiaashuu.appteka.screen.details.adapter.status.StatusItem
import com.hiaashuu.appteka.screen.details.adapter.status.StatusType
import com.hiaashuu.appteka.screen.details.adapter.user_rate.UserRateItem
import com.hiaashuu.appteka.screen.details.adapter.user_review.UserReviewItem
import com.hiaashuu.appteka.screen.details.adapter.whats_new.WhatsNewItem
import com.hiaashuu.appteka.core.permissions.CapabilityPolicy
import com.hiaashuu.appteka.screen.details.api.Details
import com.hiaashuu.appteka.screen.details.api.STATUS_MODERATION
import com.hiaashuu.appteka.screen.details.api.STATUS_NORMAL
import com.hiaashuu.appteka.screen.details.api.SECURITY_STATUS_COMPLETED
import com.hiaashuu.appteka.screen.details.api.SECURITY_STATUS_FAILED
import com.hiaashuu.appteka.screen.details.api.SECURITY_STATUS_PENDING
import com.hiaashuu.appteka.screen.details.api.SECURITY_STATUS_SCANNING
import com.hiaashuu.appteka.screen.details.api.SECURITY_VERDICT_MALWARE
import com.hiaashuu.appteka.screen.details.api.SECURITY_VERDICT_SAFE
import com.hiaashuu.appteka.screen.details.api.SECURITY_VERDICT_SUSPICIOUS
import com.hiaashuu.appteka.screen.details.api.STATUS_PRIVATE
import com.hiaashuu.appteka.screen.details.api.STATUS_UNLINKED
import com.hiaashuu.appteka.screen.details.api.Security
import com.hiaashuu.appteka.screen.details.api.TranslationResponse
import com.hiaashuu.appteka.util.NOT_INSTALLED
import java.util.Locale
import androidx.core.net.toUri

interface DetailsConverter {
    fun convert(
        details: Details,
        downloadState: Int,
        installedVersionCode: Int,
        moderation: Boolean,
        translationData: TranslationResponse?,
        translationState: Int
    ): List<Item>

    companion object {
        const val TRANSLATION_ORIGINAL: Int = 0
        const val TRANSLATION_PROGRESS: Int = 1
        const val TRANSLATION_TRANSLATED: Int = 2
    }
}

class DetailsConverterImpl(
    private val resourceProvider: DetailsResourceProvider,
    private val abiResourceProvider: AbiResourceProvider,
    private val locale: Locale
) : DetailsConverter {

    private val ID_STATUS = 1L
    private val ID_HEADER = 2L
    private val ID_CONTROLS = 3L
    private val ID_PLAY = 4L
    private val ID_SECURITY = 5L
    private val ID_SCREENSHOTS = 6L
    private val ID_USER_REVIEW = 7L
    private val ID_USER_RATE = 8L
    private val ID_WHATS_NEW = 9L
    private val ID_DESCRIPTION = 10L
    private val ID_ABI = 11L
    private val ID_PERMISSIONS = 12L
    private val ID_DISCUSS = 13L
    private val ID_SCORES = 14L
    private val ID_RATING_OFFSET = 1000L

    override fun convert(
        details: Details,
        downloadState: Int,
        installedVersionCode: Int,
        moderation: Boolean,
        translationData: TranslationResponse?,
        translationState: Int
    ): List<Item> {
        val items = ArrayList<Item>()

        when (details.info.fileStatus) {
            STATUS_UNLINKED -> items += StatusItem(
                id = ID_STATUS,
                type = StatusType.ERROR,
                text = resourceProvider.unlinkedStatusText(),
                actionType = StatusAction.NONE,
                actionLabel = null,
            )

            STATUS_PRIVATE -> {
                val canEdit = CapabilityPolicy.isAllowed(
                    action = CapabilityAction.APP_EDIT_META,
                    capabilities = details.capabilities,
                    allowOnUnknown = false,
                )
                items += StatusItem(
                    id = ID_STATUS,
                    type = StatusType.INFO,
                    text = resourceProvider.privateStatusText(),
                    actionType = if (canEdit) StatusAction.EDIT_META else StatusAction.NONE,
                    actionLabel = resourceProvider.editMetaAction(),
                )
            }

            STATUS_MODERATION -> {
                if (!moderation) {
                    val canUnpublish = CapabilityPolicy.isAllowed(
                        action = CapabilityAction.APP_UNPUBLISH,
                        capabilities = details.capabilities,
                        allowOnUnknown = false,
                    )
                    items += StatusItem(
                        id = ID_STATUS,
                        type = StatusType.WARNING,
                        text = resourceProvider.moderationStatusText(),
                        actionType = if (canUnpublish) StatusAction.UNPUBLISH else StatusAction.NONE,
                        actionLabel = resourceProvider.unpublishAction(),
                    )
                }
            }

            else -> Unit
        }

        items += HeaderItem(
            id = ID_HEADER,
            icon = details.info.icon,
            label = details.info.label.orEmpty(),
            author = details.info.author,
            downloadState = downloadState,
            versionName = details.info.version,
            versionCode = details.info.versionCode,
            size = resourceProvider.formatFileSize(details.info.size)
        )

        items += ControlsItem(
            id = ID_CONTROLS,
            appId = details.info.appId,
            packageName = details.info.packageName,
            versionCode = details.info.versionCode,
            sdkVersion = details.info.sdkVersion,
            androidVersion = details.info.androidVersion,
            size = details.info.size,
            link = details.link,
            expiresIn = details.expiresIn,
            installedVersionCode = installedVersionCode,
            downloadState = downloadState,
        )

        items += PlayItem(
            id = ID_PLAY,
            rating = details.meta?.rating,
            downloads = details.info.downloads ?: 0,
            favorites = details.info.favorites ?: 0,
            exclusive = details.meta?.exclusive == true,
            openSource = details.meta?.sourceUrl?.isNotEmpty() == true,
            official = details.developer?.isOfficial == true,
            category = details.meta?.category,
            osVersion = details.info.androidVersion,
            minSdk = details.info.sdkVersion,
            securityStatus = convertPlaySecurityStatus(details.security),
            securityScore = details.security?.score,
        )

        convertSecurityItem(ID_SECURITY, details.info.appId, details.security, resourceProvider)?.let {
            items += it
        }

        if (details.meta?.screenshots != null && details.meta.screenshots.isNotEmpty()) {
            items += ScreenshotsItem(
                id = ID_SCREENSHOTS,
                items = details.meta.screenshots.map {
                    ScreenshotItem(
                        id = it.scrId.hashCode().toLong(),
                        original = it.original.toUri(),
                        preview = it.preview.toUri(),
                        width = it.width,
                        height = it.height,
                    )
                }
            )
        }
        if (details.userRating != null) {
            items += UserReviewItem(
                id = ID_USER_REVIEW,
                score = details.userRating.score,
                text = details.userRating.text,
                time = details.userRating.time * 1000,
                user = details.userRating.user,
            )
        } else if (installedVersionCode != NOT_INSTALLED && details.info.fileStatus == STATUS_NORMAL) {
            items += UserRateItem(
                id = ID_USER_RATE,
                appId = details.info.appId,
                rateCapability = details.capabilities?.get(CapabilityAction.APP_RATE),
            )
        }
        if (!details.meta?.whatsNew.isNullOrBlank()) {
            val whatsNewText = when (translationState) {
                DetailsConverter.TRANSLATION_TRANSLATED -> translationData?.whatsNew
                else -> details.meta?.whatsNew
            }
            items += WhatsNewItem(
                id = ID_WHATS_NEW,
                text = whatsNewText.orEmpty().trim(),
            )
        }
        val descriptionText = when (translationState) {
            DetailsConverter.TRANSLATION_TRANSLATED -> translationData?.description
            else -> details.meta?.description
        }
        items += DescriptionItem(
            id = ID_DESCRIPTION,
            text = descriptionText.orEmpty().trim(),
            versionName = details.info.version,
            versionCode = details.info.versionCode,
            versionsCount = details.versions?.size ?: 0,
            uploadDate = details.info.time * 1000,
            checksum = details.info.sha1,
            sourceUrl = details.meta?.sourceUrl,
            translationState = translationState,
            packageName = details.info.packageName
        )
        if (!details.info.abi.isNullOrEmpty()) {
            items += AbiItem(
                id = ID_ABI,
                abiList = details.info.abi,
                isCompatible = abiResourceProvider.checkCompatibility(details.info.abi),
            )
        }
        if (!details.info.permissions.isNullOrEmpty()) {
            items += PermissionsItem(
                id = ID_PERMISSIONS,
                permissions = details.info.permissions,
            )
        }
        if (details.info.fileStatus == STATUS_NORMAL || !details.versions.isNullOrEmpty()) {
            items += DiscussItem(
                id = ID_DISCUSS,
                msgCount = details.msgCount,
            )
        }
        if (
            details.meta?.scores != null &&
            details.meta.rating != null &&
            details.meta.rateCount != null &&
            details.meta.rateCount > 0
        ) {
            items += ScoresItem(
                id = ID_SCORES,
                rateCount = details.meta.rateCount,
                rating = details.meta.rating,
                scores = details.meta.scores
            )
        }

        if (!details.ratingsList.isNullOrEmpty()) {
            items += details.ratingsList.map { rating ->
                RatingItem(
                    id = ID_RATING_OFFSET + rating.rateId,
                    score = rating.score,
                    text = rating.text,
                    time = rating.time * 1000,
                    user = rating.user,
                )
            }
        }

        return items
    }
}

private fun convertSecurityItem(
    id: Long,
    appId: String,
    security: Security?,
    resourceProvider: DetailsResourceProvider
): SecurityItem? {
    return if (security == null) {
        SecurityItem(
            id = id,
            appId = appId,
            type = SecurityType.NOT_SCANNED,
            text = resourceProvider.securityNotScannedText(),
            score = null,
            showAction = true,
            actionLabel = resourceProvider.requestScanAction(),
        )
    } else {
        null
    }
}

private fun convertPlaySecurityStatus(security: Security?): PlaySecurityStatus? {
    return when {
        security == null -> null
        security.status == SECURITY_STATUS_PENDING || security.status == SECURITY_STATUS_SCANNING -> {
            PlaySecurityStatus.SCANNING
        }
        security.status == SECURITY_STATUS_COMPLETED -> when (security.verdict) {
            SECURITY_VERDICT_SAFE -> PlaySecurityStatus.SAFE
            SECURITY_VERDICT_SUSPICIOUS -> PlaySecurityStatus.SUSPICIOUS
            SECURITY_VERDICT_MALWARE -> PlaySecurityStatus.MALWARE
            else -> PlaySecurityStatus.NOT_CHECKED
        }
        security.status == SECURITY_STATUS_FAILED -> PlaySecurityStatus.NOT_CHECKED
        else -> PlaySecurityStatus.NOT_CHECKED
    }
}