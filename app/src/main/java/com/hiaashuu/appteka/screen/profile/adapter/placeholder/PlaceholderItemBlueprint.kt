package com.hiaashuu.appteka.screen.profile.adapter.placeholder

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class PlaceholderItemBlueprint(override val presenter: ItemPresenter<PlaceholderItemView, PlaceholderItem>) :
    ItemBlueprint<PlaceholderItemView, PlaceholderItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.profile_block_placeholder,
        creator = { _, view -> PlaceholderItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is PlaceholderItem

}