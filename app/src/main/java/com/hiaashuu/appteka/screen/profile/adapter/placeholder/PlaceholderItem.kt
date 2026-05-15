package com.hiaashuu.appteka.screen.profile.adapter.placeholder

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaceholderItem(
    override val id: Long,
) : Item, Parcelable