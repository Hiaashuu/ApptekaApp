package com.hiaashuu.appteka.core.permissions.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.core.permissions.Capability
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
data class UserCapabilitiesResponse(
    @SerializedName("role")
    val role: Int,
    @SerializedName("access_list")
    val accessList: List<Int>?,
    @SerializedName("capabilities")
    val capabilities: Map<String, Capability>?,
)