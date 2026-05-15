package com.hiaashuu.appteka.screen.chat.adapter.outgoing

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.dto.UserMark
import com.hiaashuu.appteka.screen.chat.adapter.MsgAttachment
import kotlinx.parcelize.Parcelize

@Parcelize
data class OutgoingMsgItem(
    override val id: Long,
    val topicId: Int,
    val msgId: Int,
    val prevMsgId: Int,
    val type: Int,
    val author: UserMark,
    val text: String,
    val time: String,
    val date: String?,
    val attachments: List<MsgAttachment>?,
    val cookie: String?,
    val sent: Boolean,
    val translated: Boolean,
) : Item, Parcelable