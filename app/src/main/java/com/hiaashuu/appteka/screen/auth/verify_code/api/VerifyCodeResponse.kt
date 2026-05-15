package com.hiaashuu.appteka.screen.auth.verify_code.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.user.api.UserProfile
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
class VerifyCodeResponse(
    @SerializedName("user_info")
    val profile: UserProfile
)