package com.hiaashuu.appteka.screen.ratings.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.screen.details.api.RatingEntity
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class RatingsResponse(
    @SerializedName("entries")
    val entries: List<RatingEntity>,
) : Parcelable