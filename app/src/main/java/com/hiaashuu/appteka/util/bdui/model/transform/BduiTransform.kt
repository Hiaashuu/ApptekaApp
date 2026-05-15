package com.hiaashuu.appteka.util.bdui.model.transform

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel

sealed interface BduiTransform {
    val type: String
}

@GsonModel
data class BduiPropertyTransform(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("id")
    val id: String,
    @SerializedName("property")
    val property: String,
    @SerializedName("value")
    val value: Any
) : BduiTransform {
    companion object {
        const val TYPE = "property"
    }
}

@GsonModel
data class BduiBatchTransform(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("transforms")
    val transforms: List<BduiTransform>
) : BduiTransform {
    companion object {
        const val TYPE = "batch"
    }
}