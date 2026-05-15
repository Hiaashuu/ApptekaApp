package com.hiaashuu.appteka.screen.profile.adapter.feed

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedItem(
    override val id: Long,
    val feedCount: Int,
    val subsCount: Int,
    val pubsCount: Int,
) : Item, Parcelable