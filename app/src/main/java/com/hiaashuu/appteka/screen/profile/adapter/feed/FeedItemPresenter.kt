package com.hiaashuu.appteka.screen.profile.adapter.feed

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.profile.adapter.ItemListener

class FeedItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<FeedItemView, FeedItem> {

    override fun bindView(view: FeedItemView, item: FeedItem, position: Int) {
        view.setFeedCount(item.feedCount)
        view.setSubsCount(item.subsCount)
        view.setPubsCount(item.pubsCount)
        view.setOnFeedClickListener { listener.onFeedClick() }
        view.setOnSubsClickListener { listener.onSubscribersClick() }
        view.setOnPubsClickListener { listener.onPublishersClick() }
    }

}