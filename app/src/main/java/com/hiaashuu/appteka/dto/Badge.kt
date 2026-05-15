package com.hiaashuu.appteka.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class BadgeMark(
    @SerializedName("code")
    val code: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("color")
    val color: String?,
    @SerializedName("name")
    val name: String,
) : Parcelable

@Parcelize
@GsonModel
data class Badge(
    @SerializedName("code")
    val code: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("color")
    val color: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
) : Parcelable