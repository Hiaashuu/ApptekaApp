package com.hiaashuu.appteka.screen.store.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
class AppsListResponse(
    @SerializedName("entries")
    val files: List<AppEntity>
)