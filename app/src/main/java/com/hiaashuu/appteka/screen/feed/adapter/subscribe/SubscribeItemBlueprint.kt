package com.hiaashuu.appteka.screen.feed.adapter.subscribe

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.screen.feed.adapter.ReactionsAdapter

class SubscribeItemBlueprint(
    override val presenter: ItemPresenter<SubscribeItemView, SubscribeItem>
) : ItemBlueprint<SubscribeItemView, SubscribeItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.feed_item_subscribe,
        creator = { _, view ->
            SubscribeItemViewHolder(view, ReactionsAdapter())
        }
    )

    override fun isRelevantItem(item: Item) = item is SubscribeItem

}