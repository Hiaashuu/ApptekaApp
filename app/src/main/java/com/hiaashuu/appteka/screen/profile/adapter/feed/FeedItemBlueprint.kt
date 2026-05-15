package com.hiaashuu.appteka.screen.profile.adapter.feed

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class FeedItemBlueprint(override val presenter: ItemPresenter<FeedItemView, FeedItem>) :
    ItemBlueprint<FeedItemView, FeedItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.profile_block_feed,
        creator = { _, view -> FeedItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is FeedItem

}