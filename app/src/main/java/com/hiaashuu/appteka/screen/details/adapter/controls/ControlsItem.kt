package com.hiaashuu.appteka.screen.details.adapter.controls

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class ControlsItem(
    override val id: Long,
    val appId: String,
    val packageName: String,
    val versionCode: Int,
    val sdkVersion: Int,
    val androidVersion: String,
    val size: Long,
    val link: String,
    val expiresIn: Long,
    val installedVersionCode: Int,
    val downloadState: Int,
) : Item, Parcelable