package com.hiaashuu.appteka.screen.upload.adapter.screen_append

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScreenAppendItem(
    override val id: Long,
) : Item, Parcelable