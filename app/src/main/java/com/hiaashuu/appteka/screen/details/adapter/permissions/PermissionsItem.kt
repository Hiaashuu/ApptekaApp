package com.hiaashuu.appteka.screen.details.adapter.permissions

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class PermissionsItem(
    override val id: Long,
    val permissions: List<String>,
) : Item, Parcelable