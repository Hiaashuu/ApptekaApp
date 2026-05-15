package com.hiaashuu.appteka.screen.users.adapter.subscriber

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class SubscriberItemBlueprint(override val presenter: ItemPresenter<SubscriberItemView, SubscriberItem>) :
    ItemBlueprint<SubscriberItemView, SubscriberItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.subscriber_item,
        creator = { _, view -> SubscriberItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is SubscriberItem

}