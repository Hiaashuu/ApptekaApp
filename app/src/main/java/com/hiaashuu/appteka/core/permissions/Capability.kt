package com.hiaashuu.appteka.core.permissions

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class Capability(
    @SerializedName("allowed")
    val allowed: Boolean,
    @SerializedName("reason")
    val reason: String? = null,
    @SerializedName("blocked_by")
    val blockedBy: String? = null,
    @SerializedName("hint_key")
    val hintKey: String? = null,
) : Parcelable

object CapabilityReason {
    const val RULE = "rule"
    const val ROLE = "role"
    const val AUTH = "auth"
    const val OWNERSHIP = "ownership"
}