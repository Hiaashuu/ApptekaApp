package com.hiaashuu.appteka.screen.feed.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.user.api.UserBrief
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
data class SubscribePayload(
    @SerializedName("publisher")
    val publisher: UserBrief,
) : PostPayload