package com.hiaashuu.appteka.screen.feed.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.screen.feed.FeedDirection
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
data class FeedResponse(
    @SerializedName("posts")
    val posts: List<PostEntity>,
    @SerializedName("offset_id")
    val offsetId: Int,
    @SerializedName("direction")
    val direction: FeedDirection,
)