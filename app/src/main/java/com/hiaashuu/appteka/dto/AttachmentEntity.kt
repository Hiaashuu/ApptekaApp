package com.hiaashuu.appteka.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class AttachmentEntity(
    @SerializedName("preview_url")
    val previewUrl: String,
    @SerializedName("original_url")
    val originalUrl: String,
    @SerializedName("size")
    val size: Long,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int
) : Parcelable