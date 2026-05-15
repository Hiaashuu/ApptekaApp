package com.hiaashuu.appteka.screen.details.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.TopicEntity
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@GsonModel
@Parcelize
data class CreateTopicResponse(
    @SerializedName("topic")
    val topic: TopicEntity,
) : Parcelable