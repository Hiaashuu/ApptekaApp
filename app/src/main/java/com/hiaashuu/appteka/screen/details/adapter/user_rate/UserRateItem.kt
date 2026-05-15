package com.hiaashuu.appteka.screen.details.adapter.user_rate

import android.os.Parcelable
import com.hiaashuu.appteka.core.permissions.Capability
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRateItem(
    override val id: Long,
    val appId: String,

    val rateCapability: Capability? = null,
) : Item, Parcelable