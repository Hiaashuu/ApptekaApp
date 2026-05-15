package com.hiaashuu.appteka.screen.topics.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.TopicEntity
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
class TopicsResponse(
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("entries")
    val topics: List<TopicEntity>
)