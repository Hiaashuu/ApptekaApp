package com.hiaashuu.appteka.screen.downloads.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
class DownloadsResponse(
    @SerializedName("entries")
    val files: List<AppEntity>
)