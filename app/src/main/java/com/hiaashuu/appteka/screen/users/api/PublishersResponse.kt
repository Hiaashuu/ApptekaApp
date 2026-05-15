package com.hiaashuu.appteka.screen.users.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
class PublishersResponse(
    @SerializedName("entries")
    val entries: List<PublisherEntity>
)