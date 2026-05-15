package com.hiaashuu.appteka.screen.feed.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.Screenshot
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
data class TextPayload(
    @SerializedName("screenshots")
    val screenshots: List<Screenshot>,
    @SerializedName("text")
    val text: String,
): PostPayload