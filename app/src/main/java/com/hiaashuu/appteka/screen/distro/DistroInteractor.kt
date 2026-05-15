package com.hiaashuu.appteka.screen.distro

import android.net.Uri
import com.hiaashuu.appteka.core.StreamsProvider
import com.hiaashuu.appteka.download.ApkStorage
import com.hiaashuu.appteka.upload.UploadApk
import com.hiaashuu.appteka.upload.UploadPackage
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.io.File

interface DistroInteractor {

    fun listDistroApps(): Observable<List<DistroAppEntity>>

    fun getPackagePermissions(fileName: String): List<String>

    fun getPackageUploadInfo(fileName: String): Pair<UploadPackage, UploadApk>?

    fun removeApk(fileName: String): Observable<Unit>

    fun copyFile(source: String, target: Uri): Observable<Unit>

}

class DistroInteractorImpl(
    private val infoProvider: DistroInfoProvider,
    private val apkStorage: ApkStorage,
    private val streamsProvider: StreamsProvider,
    private val schedulers: SchedulersFactory
) : DistroInteractor {

    override fun listDistroApps(): Observable<List<DistroAppEntity>> {
        return Single
            .create {
                val items = infoProvider.getApkItems()
                it.onSuccess(items)
            }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun getPackagePermissions(fileName: String): List<String> {
        return infoProvider.getPackagePermissions(fileName)
    }

    override fun getPackageUploadInfo(fileName: String): Pair<UploadPackage, UploadApk>? {
        return infoProvider.getPackageUploadInfo(fileName)
    }

    override fun removeApk(fileName: String): Observable<Unit> {
        return Single
            .create {
                apkStorage.delete(fileName)
                it.onSuccess(Unit)
            }
            .toObservable()
            .subscribeOn(schedulers.io())
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

}