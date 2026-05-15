package com.hiaashuu.appteka.screen.chat.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.MessageEntity
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
data class HistoryResponse(
    @SerializedName("messages")
    val messages: List<MessageEntity>
)