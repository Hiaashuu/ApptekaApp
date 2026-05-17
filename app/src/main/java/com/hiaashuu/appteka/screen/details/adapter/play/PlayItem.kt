package com.hiaashuu.appteka.screen.details.adapter.play

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.categories.Category
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayItem(
    override val id: Long,
    val rating: Float?,
    val downloads: Int,
    val favorites: Int,
    val exclusive: Boolean,
    val openSource: Boolean,
    val official: Boolean,
    val category: Category?,
    val osVersion: String?,
    val minSdk: Int?,
    val securityStatus: PlaySecurityStatus?,
    val securityScore: Int?,
) : Item, Parcelable

enum class PlaySecurityStatus {
    SCANNING,
    SAFE,
    SUSPICIOUS,
    MALWARE,
    NOT_CHECKED
}