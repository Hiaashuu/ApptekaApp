package com.hiaashuu.appteka.screen.users.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
class SubscribersResponse(
    @SerializedName("entries")
    val entries: List<SubscriberEntity>
)