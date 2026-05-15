package com.hiaashuu.appteka.screen.chat.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
data class MsgTranslateResponse(
    @SerializedName("text")
    val text: String,
    @SerializedName("lang")
    val lang: String,
)