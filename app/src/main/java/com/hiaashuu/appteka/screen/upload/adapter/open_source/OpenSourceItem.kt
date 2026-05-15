package com.hiaashuu.appteka.screen.upload.adapter.open_source

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class OpenSourceItem(
    override val id: Long,
    val value: Boolean,
    val url: String,
) : Item, Parcelable