package com.hiaashuu.appteka.screen.chat.adapter.incoming

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.dto.UserMark
import com.hiaashuu.appteka.screen.chat.adapter.MsgAttachment
import kotlinx.parcelize.Parcelize

@Parcelize
data class IncomingMsgItem(
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
    val translated: Boolean,
) : Item, Parcelable