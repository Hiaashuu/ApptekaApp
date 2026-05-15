package com.hiaashuu.appteka.screen.home.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class StartupResponse(
    @SerializedName("update")
    val update: AppEntity?,
    @SerializedName("unread")
    val unread: Int,
    @SerializedName("feed")
    val feed: Int,
    @SerializedName("moderation")
    val moderation: ModerationData?,
    @SerializedName("bdui")
    val bdui: StartupBdui?,
) : Parcelable

@Parcelize
@GsonModel
data class StartupBdui(
    @SerializedName("url")
    val url: String,
    @SerializedName("title")
    val title: String?,
) : Parcelable