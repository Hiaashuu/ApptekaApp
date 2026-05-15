package com.hiaashuu.appteka.screen.profile.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@GsonModel
@Parcelize
data class ProfileResponse(
    @SerializedName("profile")
    val profile: Profile,
) : Parcelable