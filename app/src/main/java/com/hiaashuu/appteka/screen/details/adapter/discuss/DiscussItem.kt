package com.hiaashuu.appteka.screen.details.adapter.discuss

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiscussItem(
    override val id: Long,
    val msgCount: Int?,
) : Item, Parcelable