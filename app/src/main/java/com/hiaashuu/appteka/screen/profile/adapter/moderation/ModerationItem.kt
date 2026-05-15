package com.hiaashuu.appteka.screen.profile.adapter.moderation

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModerationItem(
    override val id: Long,
    val count: Int,
) : Item, Parcelable