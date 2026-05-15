package com.hiaashuu.appteka.screen.uploads.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
class UploadsResponse(
    @SerializedName("entries")
    val files: List<AppEntity>
)