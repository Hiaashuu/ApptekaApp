package com.hiaashuu.appteka.screen.profile.adapter.reviews

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.screen.profile.adapter.review.ReviewItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewsItem(
    override val id: Long,
    val count: Int,
    val items: List<ReviewItem>,
) : Item, Parcelable