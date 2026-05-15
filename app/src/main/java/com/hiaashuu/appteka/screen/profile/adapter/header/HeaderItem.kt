package com.hiaashuu.appteka.screen.profile.adapter.header

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.dto.Badge
import com.hiaashuu.appteka.dto.BadgeMark
import com.hiaashuu.appteka.dto.UserIcon
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeaderItem(
    override val id: Long,
    val userName: String?,
    val userEmail: String?,
    val userBio: String?,
    val userIcon: UserIcon,
    val joinTime: Long,
    val lastSeen: Long,
    val role: Int,
    val isRegistered: Boolean,
    val isVerified: Boolean,
    val isSelf: Boolean,
    val isSubscribed: Boolean,
    val nameRegex: String?,
    val primaryBadge: BadgeMark? = null,
    val badges: List<Badge>? = null,
) : Item, Parcelable