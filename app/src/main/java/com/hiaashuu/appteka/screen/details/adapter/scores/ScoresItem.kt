package com.hiaashuu.appteka.screen.details.adapter.scores

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.screen.details.api.Scores
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScoresItem(
    override val id: Long,
    val rateCount: Int,
    val rating: Float,
    val scores: Scores,
) : Item, Parcelable