package com.hiaashuu.appteka.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class UserMark(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("icon")
    val icon: UserIcon? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("primary_badge")
    val primaryBadge: BadgeMark? = null,
) : Parcelable