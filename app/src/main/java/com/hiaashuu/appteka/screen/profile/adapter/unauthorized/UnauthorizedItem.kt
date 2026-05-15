package com.hiaashuu.appteka.screen.profile.adapter.unauthorized

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnauthorizedItem(
    override val id: Long,
) : Item, Parcelable