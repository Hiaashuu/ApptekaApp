package com.hiaashuu.appteka.screen.details.adapter.whats_new

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class WhatsNewItemBlueprint(override val presenter: ItemPresenter<WhatsNewItemView, WhatsNewItem>) :
    ItemBlueprint<WhatsNewItemView, WhatsNewItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.details_block_whats_new,
        creator = { _, view -> WhatsNewItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is WhatsNewItem

}