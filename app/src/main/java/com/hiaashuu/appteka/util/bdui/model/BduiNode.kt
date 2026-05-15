package com.hiaashuu.appteka.util.bdui.model

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import com.hiaashuu.appteka.util.bdui.model.action.BduiAction

interface BduiNode {
    val id: String
    val type: String
    val layoutParams: BduiLayoutParams?
    val action: BduiAction?
}

@GsonModel
data class BduiRef(
    @SerializedName("type")
    val type: String = "ref",
    @SerializedName("id")
    val id: String,
    @SerializedName("property")
    val property: String
)