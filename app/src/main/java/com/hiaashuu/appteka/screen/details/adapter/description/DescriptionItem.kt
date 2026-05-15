package com.hiaashuu.appteka.screen.details.adapter.description

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class DescriptionItem(
    override val id: Long,
    val text: String,
    val versionName: String,
    val versionCode: Int,
    val versionsCount: Int,
    val uploadDate: Long,
    val checksum: String,
    val sourceUrl: String?,
    val translationState: Int,
) : Item, Parcelable