package com.hiaashuu.appteka.screen.profile.adapter.header

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class HeaderItemBlueprint(override val presenter: ItemPresenter<HeaderItemView, HeaderItem>) :
    ItemBlueprint<HeaderItemView, HeaderItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.profile_block_header,
        creator = { _, view -> HeaderItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is HeaderItem

}