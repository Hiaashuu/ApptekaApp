package com.hiaashuu.appteka.screen.profile.adapter.favorites

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoritesItem(
    override val id: Long,
    val count: Int,
) : Item, Parcelable