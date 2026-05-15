package com.hiaashuu.appteka.screen.profile.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@GsonModel
@Parcelize
data class EliminateUserResponse(
    @SerializedName("files_count")
    val filesCount: Int,
    @SerializedName("msgs_count")
    val msgsCount: Int,
    @SerializedName("ratings_count")
    val ratingsCount: Int,
) : Parcelable