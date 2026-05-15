package com.hiaashuu.appteka.screen.users.adapter.publisher

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class PublisherItemBlueprint(override val presenter: ItemPresenter<PublisherItemView, PublisherItem>) :
    ItemBlueprint<PublisherItemView, PublisherItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.publisher_item,
        creator = { _, view -> PublisherItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is PublisherItem

}