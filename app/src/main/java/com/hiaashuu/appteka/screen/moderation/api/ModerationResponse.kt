package com.hiaashuu.appteka.screen.moderation.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
class ModerationResponse(
    @SerializedName("entries")
    val files: List<AppEntity>
)