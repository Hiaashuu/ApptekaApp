package com.hiaashuu.appteka.screen.auth.request_code.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
class RequestCodeResponse(
    @SerializedName("registered")
    val registered: Boolean,
    @SerializedName("request_id")
    val requestId: String,
    @SerializedName("code_regex")
    val codeRegex: String,
    @SerializedName("name_regex")
    val nameRegex: String,
)