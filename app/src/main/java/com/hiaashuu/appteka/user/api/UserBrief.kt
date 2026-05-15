package com.hiaashuu.appteka.user.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.BadgeMark
import com.hiaashuu.appteka.dto.UserIcon
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@GsonModel
@Parcelize
data class UserBrief(
    @SerializedName("id")
    val id: Int,
    @SerializedName("icon")
    val icon: UserIcon,
    @SerializedName("join_time")
    val joinTime: Long,
    @SerializedName("last_seen")
    val lastSeen: Long,
    @SerializedName("role")
    val role: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("is_registered")
    val isRegistered: Boolean,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("url")
    val url: String?,
    @SerializedName("primary_badge")
    val primaryBadge: BadgeMark? = null,
    @SerializedName("bio")
    val bio: String? = null,
) : Parcelable