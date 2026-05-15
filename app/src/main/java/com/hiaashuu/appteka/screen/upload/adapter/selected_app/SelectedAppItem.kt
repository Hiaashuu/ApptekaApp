package com.hiaashuu.appteka.screen.upload.adapter.selected_app

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.upload.UploadApk
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectedAppItem(
    override val id: Long,
    val apk: UploadApk
) : Item, Parcelable