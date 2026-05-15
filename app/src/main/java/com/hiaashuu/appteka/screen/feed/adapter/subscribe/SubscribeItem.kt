package com.hiaashuu.appteka.screen.feed.adapter.subscribe

import com.hiaashuu.appteka.screen.feed.adapter.FeedItem
import com.hiaashuu.appteka.screen.feed.api.Reaction
import com.hiaashuu.appteka.user.api.UserBrief
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubscribeItem(
    override val id: Long,
    val time: Long,
    val publisher: UserBrief,
    override val user: UserBrief,
    override val actions: List<String>?,
    val reacts: List<Reaction>?,
    override var hasMore: Boolean = false,
    override var hasProgress: Boolean = false,
) : FeedItem {

    override fun getReactions(): List<Reaction>? = reacts

    override fun withReactions(reactions: List<Reaction>): FeedItem = copy(reacts = reactions)
}