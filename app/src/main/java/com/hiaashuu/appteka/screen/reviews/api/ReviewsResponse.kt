package com.hiaashuu.appteka.screen.reviews.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class ReviewsResponse(
    @SerializedName("entries")
    val entries: List<ReviewEntity>,
) : Parcelable