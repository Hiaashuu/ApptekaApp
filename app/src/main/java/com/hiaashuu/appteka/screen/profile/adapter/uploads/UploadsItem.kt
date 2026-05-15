package com.hiaashuu.appteka.screen.profile.adapter.uploads

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.screen.profile.adapter.app.AppItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class UploadsItem(
    override val id: Long,
    val userId: Int,
    val uploads: Int,
    val downloads: Int,
    val items: List<AppItem>,
) : Item, Parcelable