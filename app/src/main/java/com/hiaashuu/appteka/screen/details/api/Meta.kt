package com.hiaashuu.appteka.screen.details.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.categories.Category
import com.hiaashuu.appteka.dto.Screenshot
import com.hiaashuu.appteka.dto.UserMark
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@GsonModel
@Parcelize
data class Meta(
    @SerializedName("category")
    val category: Category?,
    @SerializedName("whats_new")
    val whatsNew: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("exclusive")
    val exclusive: Boolean?,
    @SerializedName("open_source")
    val openSource: Boolean?,
    @SerializedName("source_url")
    val sourceUrl: String?,
    @SerializedName("screenshots")
    val screenshots: List<Screenshot>?,
    @SerializedName("similar")
    val similar: Boolean?,
    @SerializedName("time")
    val time: Long?,
    @SerializedName("author")
    val author: UserMark? = null,
    @SerializedName("rate_count")
    val rateCount: Int?,
    @SerializedName("rating")
    val rating: Float?,
    @SerializedName("scores")
    val scores: Scores?,
) : Parcelable