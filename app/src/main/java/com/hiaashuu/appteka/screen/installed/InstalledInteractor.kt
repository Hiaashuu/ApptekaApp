package com.hiaashuu.appteka.screen.installed

import android.net.Uri
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.core.StreamsProvider
import com.hiaashuu.appteka.download.ApkStorage
import com.hiaashuu.appteka.screen.installed.api.CheckUpdatesRequest
import com.hiaashuu.appteka.screen.installed.api.UpdateEntity
import com.hiaashuu.appteka.upload.UploadApk
import com.hiaashuu.appteka.upload.UploadPackage
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.io.File
import java.util.Locale

interface InstalledInteractor {

    fun listInstalledApps(systemApps: Boolean): Observable<List<InstalledAppEntity>>

    fun getUpdates(apps: Map<String, Long>): Observable<List<UpdateEntity>>

    fun getPackagePermissions(packageName: String): List<String>

    fun getPackageUploadInfo(packageName: String): Pair<UploadPackage, UploadApk>

    fun copyFile(source: String, target: Uri): Observable<Unit>

    fun copyToStorage(source: String, fileName: String): Observable<Uri>

}

class InstalledInteractorImpl(
    private val api: StoreApi,
    private val locale: Locale,
    private val apkStorage: ApkStorage,
    private val streamsProvider: StreamsProvider,
    private val infoProvider: InstalledInfoProvider,
    private val schedulers: SchedulersFactory
) : InstalledInteractor {

    override fun listInstalledApps(systemApps: Boolean): Observable<List<InstalledAppEntity>> {
        return Single
            .create {
                it.onSuccess(
                    infoProvider
                        .getInstalledApps()
                        .filter { app -> app.isUserApp || systemApps })
            }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun getUpdates(apps: Map<String, Long>): Observable<List<UpdateEntity>> {
        val request = CheckUpdatesRequest(
            locale = locale.language,
            apps = apps,
        )
        return api.checkUpdates(request)
            .map { response ->
                response.result.entries ?: emptyList()
            }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun getPackagePermissions(packageName: String): List<String> {
        return infoProvider.getPackagePermissions(packageName)
    }

    override fun getPackageUploadInfo(packageName: String): Pair<UploadPackage, UploadApk> {
        return infoProvider.getPackageUploadInfo(packageName)
    }

    override fun copyFile(source: String, target: Uri): Observable<Unit> {
        return Single
            .create { emitter ->
                try {
                    val srcFile = File(source)
                    streamsProvider.openInputStream(Uri.fromFile(srcFile))?.let { input ->
                        streamsProvider.openOutputStream(target)?.let { output ->
                            input.copyTo(output)
                            output.flush()
                            emitter.onSuccess(Unit)
                        } ?: emitter.onError(Throwable("Output stream opening error"))
                    } ?: emitter.onError(Throwable("Input stream opening error"))
                } catch (ex: Throwable) {
                    emitter.onError(ex)
                }
            }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun copyToStorage(source: String, fileName: String): Observable<Uri> {
        return Single
            .create { emitter ->
                try {
                    val srcFile = File(source)
                    srcFile.inputStream().use { input ->
                        val uri = apkStorage.copyToStorage(input, fileName)
                        if (uri != null) {
                            emitter.onSuccess(uri)
                        } else {
                            emitter.onError(Throwable("Failed to copy to storage"))
                        }
                    }
                } catch (ex: Throwable) {
                    emitter.onError(ex)
                }
            }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}