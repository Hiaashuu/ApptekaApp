package com.hiaashuu.appteka.screen.upload.adapter.submit

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubmitItem(
    override val id: Long,
    val editMode: Boolean,
    val enabled: Boolean,
) : Item, Parcelable