package com.hiaashuu.appteka.screen.chat.adapter.system

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class SystemMsgItem(
    override val id: Long,
    val text: String,
    val date: String?,
) : Item, Parcelable