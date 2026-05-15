package com.hiaashuu.appteka.screen.post.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.screen.feed.api.Reaction
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class FeedConfigResponse(
    @SerializedName("post_max_length")
    val postMaxLength: Int,
    @SerializedName("post_max_images")
    val postMaxImages: Int,
    @SerializedName("reactions")
    val reactions: List<Reaction>?,
) : Parcelable