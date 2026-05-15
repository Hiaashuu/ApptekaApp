package com.hiaashuu.appteka.screen.details.adapter.description

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class DescriptionItemBlueprint(override val presenter: ItemPresenter<DescriptionItemView, DescriptionItem>) :
    ItemBlueprint<DescriptionItemView, DescriptionItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.details_block_description,
        creator = { _, view -> DescriptionItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is DescriptionItem

}