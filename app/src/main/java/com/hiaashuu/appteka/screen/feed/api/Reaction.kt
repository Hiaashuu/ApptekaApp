package com.hiaashuu.appteka.screen.feed.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@GsonModel
@Parcelize
data class Reaction(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("active")
    val active: Boolean?,
) : Parcelable