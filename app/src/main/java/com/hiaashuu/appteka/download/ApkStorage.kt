package com.hiaashuu.appteka.download

import android.net.Uri
import java.io.InputStream
import java.io.OutputStream

data class ApkInfo(
    val fileName: String,
    val uri: Uri,
    val size: Long,
    val lastModified: Long,
)

interface ApkStorage {

    fun openWrite(fileName: String): OutputStream

    fun commit(fileName: String): Boolean

    fun openRead(fileName: String): InputStream?

    fun getInstallUri(fileName: String): Uri?

    fun exists(fileName: String): Boolean

    fun delete(fileName: String): Boolean

    fun deleteTmp(fileName: String): Boolean

    fun getTmpSize(fileName: String): Long

    fun openAppend(fileName: String): OutputStream

    fun listApkFiles(): List<ApkInfo>

    fun clearAll(): Int

    fun copyToStorage(input: InputStream, fileName: String): Uri?

    fun getFilePath(fileName: String): String?

    fun isPermissionRequired(): Boolean

}