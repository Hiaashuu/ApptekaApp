package com.hiaashuu.appteka.screen.feed.adapter

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.screen.feed.api.Reaction
import com.hiaashuu.appteka.user.api.UserBrief

interface FeedItem : Item, Parcelable {
    val user: UserBrief?
    val actions: List<String>?
    var hasMore: Boolean
    var hasProgress: Boolean

    fun getReactions(): List<Reaction>?

    fun withReactions(reactions: List<Reaction>): FeedItem
}