package com.hiaashuu.appteka.screen.permissions.adapter.unsafe

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsafePermissionItem(
    override val id: Long,
    val description: String?,
    val permission: String,
) : Item, Parcelable