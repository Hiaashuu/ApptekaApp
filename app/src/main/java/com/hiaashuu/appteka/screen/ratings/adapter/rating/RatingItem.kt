package com.hiaashuu.appteka.screen.ratings.adapter.rating

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.dto.UserMark
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatingItem(
    override val id: Long,
    val rateId: Int,
    val score: Int,
    val text: String?,
    val time: Long,
    val user: UserMark,
    val showRatingMenu: Boolean,
    var hasMore: Boolean = false,
    var hasError: Boolean = false,
    var hasProgress: Boolean = false,
) : Item, Parcelable