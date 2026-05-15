package com.hiaashuu.appteka.screen.reviews.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.screen.details.api.RatingEntity
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class ReviewEntity(
    @SerializedName("file")
    val file: AppEntity,
    @SerializedName("rating")
    val rating: RatingEntity,
) : Parcelable