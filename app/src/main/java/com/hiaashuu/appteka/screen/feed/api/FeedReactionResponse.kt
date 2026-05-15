package com.hiaashuu.appteka.screen.feed.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
data class FeedReactionResponse(
    @SerializedName("reactions")
    val reactions: Map<String, Reaction>
)