package com.hiaashuu.appteka.screen.details.adapter.discuss

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class DiscussItemBlueprint(override val presenter: ItemPresenter<DiscussItemView, DiscussItem>) :
    ItemBlueprint<DiscussItemView, DiscussItem> {

    override val viewHolderProvider =
        ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.details_block_discuss,
            creator = { _, view -> DiscussItemViewHolder(view) }
        )

    override fun isRelevantItem(item: Item) = item is DiscussItem

}