package com.hiaashuu.appteka.screen.home.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class StatusResponse(
    @SerializedName("block")
    val block: Boolean?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("bdui")
    val bdui: StartupBdui?,
) : Parcelable