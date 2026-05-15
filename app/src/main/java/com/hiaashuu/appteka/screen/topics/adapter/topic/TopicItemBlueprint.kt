package com.hiaashuu.appteka.screen.topics.adapter.topic

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class TopicItemBlueprint(override val presenter: ItemPresenter<TopicItemView, TopicItem>) :
    ItemBlueprint<TopicItemView, TopicItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.topic_item,
        creator = { _, view -> TopicItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is TopicItem

}