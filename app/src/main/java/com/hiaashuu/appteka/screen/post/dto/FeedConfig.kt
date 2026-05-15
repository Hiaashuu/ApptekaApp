package com.hiaashuu.appteka.screen.post.dto

import android.os.Parcelable
import com.hiaashuu.appteka.screen.feed.api.Reaction
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedConfig(
    val postMaxLength: Int,
    val postMaxImages: Int,
    val reactions: List<Reaction>?,
) : Parcelable