package com.hiaashuu.appteka.screen.feed.adapter

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.dto.Screenshot
import com.hiaashuu.appteka.screen.feed.api.Reaction
import com.hiaashuu.appteka.user.api.UserBrief

interface ItemListener {

    fun onItemClick(item: Item)

    fun onLoadMore(item: Item)

    fun onImageClick(items: List<Screenshot>, clicked: Int)

    fun onAppClick(appId: String, title: String?)

    fun onUserClick(user: UserBrief)

    fun onMenuClick(item: FeedItem)

    fun onLoginClick()

    fun onReactionClick(item: FeedItem, reaction: Reaction)

}