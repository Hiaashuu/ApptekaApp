package com.hiaashuu.appteka.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class UserIcon(
    @SerializedName("icon")
    val icon: String,

    @SerializedName("label")
    val label: Map<String, String>? = null,
    @SerializedName("color")
    val color: String,

    @SerializedName("image")
    val image: Avatar? = null,
) : Parcelable