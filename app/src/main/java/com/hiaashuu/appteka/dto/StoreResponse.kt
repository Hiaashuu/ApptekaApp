package com.hiaashuu.appteka.dto

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
data class StoreResponse<A>(
    @SerializedName("status")
    val status: Int,
    @SerializedName("result")
    val result: A,
    @SerializedName("description")
    val description: String
)