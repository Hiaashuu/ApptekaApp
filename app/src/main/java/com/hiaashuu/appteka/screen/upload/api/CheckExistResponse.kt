package com.hiaashuu.appteka.screen.upload.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.screen.details.api.AppVersion
import com.hiaashuu.appteka.screen.details.api.Meta
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class CheckExistResponse(
    @SerializedName("info")
    val info: String?,
    @SerializedName("warning")
    val warning: String?,
    @SerializedName("error")
    val error: String?,
    @SerializedName("file")
    val file: AppEntity?,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("versions")
    val versions: List<AppVersion>?,
) : Parcelable