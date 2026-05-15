package com.hiaashuu.appteka.upload

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@GsonModel
@Parcelize
data class UploadScreenshotsResponse(
    @SerializedName("scr_ids")
    val scrIds: List<String>,
) : Parcelable