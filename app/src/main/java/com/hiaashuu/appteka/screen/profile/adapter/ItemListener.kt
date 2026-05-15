package com.hiaashuu.appteka.screen.profile.adapter

import com.hiaashuu.appteka.dto.Badge
import com.hiaashuu.appteka.screen.profile.adapter.app.AppItem

interface ItemListener {

    fun onAppClick(appId: String, title: String?)

    fun onRatingsClick()

    fun onFavoritesClick()

    fun onDownloadsClick()

    fun onUploadsClick(userId: Int)

    fun onModerationClick()

    fun onLoginClick()

    fun onEditName(name: String?, nameRegex: String?)

    fun onEditAvatar()

    fun onEditEmail()

    fun onNextPage(last: AppItem, param: (List<AppItem>) -> Unit)

    fun onFeedClick()

    fun onSubscribersClick()

    fun onPublishersClick()

    fun onSubscribeClick()

    fun onUnsubscribeClick()

    fun onBadgeClick(badge: Badge)

}