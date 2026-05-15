package com.hiaashuu.appteka.screen.moderation

import com.hiaashuu.appteka.categories.CategoryConverter
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiResourceProvider
import com.hiaashuu.appteka.screen.moderation.adapter.app.AppItem

interface AppConverter {

    fun convert(appEntity: AppEntity): AppItem

}

class AppConverterImpl(
    private val resourceProvider: AppsResourceProvider,
    private val categoryConverter: CategoryConverter,
    private val abiResourceProvider: AbiResourceProvider
) : AppConverter {

    private var id: Long = 1

    override fun convert(appEntity: AppEntity): AppItem {
        val isAbiCompatible = appEntity.abi?.let { abiResourceProvider.checkCompatibility(it) } ?: true
        return AppItem(
            id = id++,
            appId = appEntity.appId,
            icon = appEntity.icon,
            title = appEntity.title,
            version = resourceProvider.formatAppVersion(appEntity.verName, appEntity.verCode),
            size = resourceProvider.formatFileSize(appEntity.size),
            rating = appEntity.rating,
            downloads = appEntity.downloads,
            category = appEntity.category?.let { categoryConverter.convert(it) },
            openSource = !appEntity.sourceUrl.isNullOrEmpty(),
            isAbiCompatible = isAbiCompatible,
        )
    }

}