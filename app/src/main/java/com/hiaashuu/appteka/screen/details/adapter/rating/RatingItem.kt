package com.hiaashuu.appteka.screen.details.adapter.rating

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.dto.UserMark
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatingItem(
    override val id: Long,
    val score: Int,
    val text: String?,
    val time: Long,
    val user: UserMark,
) : Item, Parcelable