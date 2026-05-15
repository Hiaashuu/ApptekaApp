package com.hiaashuu.appteka.screen.chat.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.TopicEntity
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
data class TopicInfoResponse(
    @SerializedName("topic")
    val topic: TopicEntity
)