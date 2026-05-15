package com.hiaashuu.appteka.screen.upload.adapter.description

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class DescriptionItem(
    override val id: Long,
    val text: String,
    val errorRequiredField: Boolean,
    val errorMinLength: Boolean,
) : Item, Parcelable