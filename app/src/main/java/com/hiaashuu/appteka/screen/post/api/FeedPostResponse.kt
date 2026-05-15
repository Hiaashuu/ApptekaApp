package com.hiaashuu.appteka.screen.post.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class FeedPostResponse(
    @SerializedName("id")
    val postId: Int
) : Parcelable