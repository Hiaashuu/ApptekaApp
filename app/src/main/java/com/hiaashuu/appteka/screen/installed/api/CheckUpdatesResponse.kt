package com.hiaashuu.appteka.screen.installed.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
data class CheckUpdatesResponse(
    @SerializedName(value = "entries")
    val entries: List<UpdateEntity>?,
)