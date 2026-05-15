package com.hiaashuu.appteka.screen.topics.adapter.topic

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.dto.BadgeMark
import com.hiaashuu.appteka.dto.UserIcon
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopicItem(
    override val id: Long,
    val icon: String,
    val title: String,
    val description: String?,
    val packageName: String?,
    val isPinned: Boolean,
    val hasUnread: Boolean,
    val lastMsgId: Int,
    val lastMsgText: String,
    val lastMsgUserIcon: UserIcon?,
    val lastMsgUserBadge: BadgeMark?,
    val hasTranslation: Boolean,
    val translated: Boolean,
    var hasMore: Boolean = false,
    var hasProgress: Boolean = false,
) : Item, Parcelable