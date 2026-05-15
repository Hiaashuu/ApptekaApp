package com.hiaashuu.appteka.screen.details.adapter.abi

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class AbiItem(
    override val id: Long,
    val abiList: List<String>,
    val isCompatible: Boolean,
) : Item, Parcelable