package com.hiaashuu.appteka.screen.upload.adapter.select_app

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectAppItem(
    override val id: Long
) : Item, Parcelable