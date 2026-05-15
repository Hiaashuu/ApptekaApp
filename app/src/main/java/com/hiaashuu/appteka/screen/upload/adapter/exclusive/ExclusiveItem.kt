package com.hiaashuu.appteka.screen.upload.adapter.exclusive

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExclusiveItem(
    override val id: Long,
    val value: Boolean,
) : Item, Parcelable