package com.hiaashuu.appteka.screen.profile.adapter.app

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppItem(
    override val id: Long,
    val appId: String,
    val icon: String?,
    val title: String,
    val rating: Float,
) : Item, Parcelable