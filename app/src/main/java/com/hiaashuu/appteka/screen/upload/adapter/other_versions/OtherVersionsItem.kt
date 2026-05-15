package com.hiaashuu.appteka.screen.upload.adapter.other_versions

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class OtherVersionsItem(
    override val id: Long,
    val versions: List<VersionItem>,
) : Item, Parcelable

@Parcelize
data class VersionItem(
    val versionId: Int,
    val appId: String,
    val title: String,
    val compatible: Boolean,
    val newer: Boolean,
) : Parcelable