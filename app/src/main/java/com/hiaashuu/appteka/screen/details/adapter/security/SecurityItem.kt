package com.hiaashuu.appteka.screen.details.adapter.security

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class SecurityItem(
    override val id: Long,
    val appId: String,
    val type: SecurityType,
    val text: String,
    val score: Int?,
    val showAction: Boolean,
    val actionLabel: String?,
) : Item, Parcelable

enum class SecurityType {
    NOT_SCANNED,
    SCANNING,
    SAFE,
    SUSPICIOUS,
    MALWARE,
    UNKNOWN
}