package com.hiaashuu.appteka.screen.profile.adapter.downloads

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class DownloadsItem(
    override val id: Long,
    val count: Int,
) : Item, Parcelable