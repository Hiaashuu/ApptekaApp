package com.hiaashuu.appteka.screen.details.adapter.header

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.dto.UserMark
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeaderItem(
    override val id: Long,
    val icon: String?,
    val label: String,
    val author: UserMark?,
    val downloadState: Int,
    val versionName: String = "",
    val versionCode: Int = 0,
    val size: String = "",
) : Item, Parcelable