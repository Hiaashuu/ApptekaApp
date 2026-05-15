package com.hiaashuu.appteka.screen.distro

import com.hiaashuu.appteka.core.PackageInfoProvider
import com.hiaashuu.appteka.download.ApkInfo
import com.hiaashuu.appteka.download.ApkStorage
import com.hiaashuu.appteka.upload.UploadApk
import com.hiaashuu.appteka.upload.UploadPackage
import com.hiaashuu.appteka.util.createApkIconURI
import com.hiaashuu.appteka.util.versionCodeCompat

interface DistroInfoProvider {

    fun getApkItems(): List<DistroAppEntity>

    fun getPackagePermissions(fileName: String): List<String>

    fun getPackageUploadInfo(fileName: String): Pair<UploadPackage, UploadApk>?

}

class DistroInfoProviderImpl(
    private val apkStorage: ApkStorage,
    private val packageInfoProvider: PackageInfoProvider,
) : DistroInfoProvider {

    override fun getApkItems(): List<DistroAppEntity> {
        return apkStorage.listApkFiles()
            .map { apkInfo -> processApk(apkInfo) }
    }

    override fun getPackagePermissions(fileName: String): List<String> {
        val path = apkStorage.getFilePath(fileName) ?: return emptyList()
        return packageInfoProvider.getPackagePermissions(path)
    }

    private fun processApk(apkInfo: ApkInfo): DistroAppEntity {
        val path = apkStorage.getFilePath(apkInfo.fileName)
        val packageInfo = path?.let { packageInfoProvider.getPackageInfo(it) }
        val appInfo = packageInfo?.applicationInfo

        return DistroAppEntity(
            packageName = appInfo?.packageName ?: "",
            label = appInfo?.let { packageInfoProvider.getApplicationLabel(it) }
                ?: apkInfo.fileName,
            icon = path?.let { createApkIconURI(it) },
            verName = packageInfo?.versionName.orEmpty(),
            verCode = packageInfo?.versionCodeCompat() ?: 0,
            lastModified = apkInfo.lastModified,
            size = apkInfo.size,
            path = path,
            fileName = apkInfo.fileName,
        )
    }

    override fun getPackageUploadInfo(fileName: String): Pair<UploadPackage, UploadApk>? {
        val path = apkStorage.getFilePath(fileName) ?: return null
        val apkInfo = apkStorage.listApkFiles().find { it.fileName == fileName } ?: return null
        val packageInfo = packageInfoProvider.getPackageInfo(path) ?: return null

        val pkg = UploadPackage(
            uniqueId = fileName,
            sha1 = null,
            packageName = packageInfo.packageName,
            size = apkInfo.size
        )
        val apk = UploadApk(
            path = path,
            version = packageInfo.versionName.orEmpty(),
            size = apkInfo.size,
            packageInfo = packageInfo
        )
        return Pair(pkg, apk)
    }
}