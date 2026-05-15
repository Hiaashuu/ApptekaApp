package com.hiaashuu.appteka.screen.profile.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import com.hiaashuu.appteka.dto.AppEntity
import kotlinx.parcelize.Parcelize

@GsonModel
@Parcelize
class UserAppsResponse(
    @SerializedName("entries")
    val files: List<AppEntity>
) : Parcelable