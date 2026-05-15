package com.hiaashuu.appteka.screen.upload.adapter.notice

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoticeItem(
    override val id: Long,
    val type: NoticeType,
    val text: String,
    val clickable: Boolean,
) : Item, Parcelable

enum class NoticeType {
    INFO,
    WARNING,
    ERROR
}