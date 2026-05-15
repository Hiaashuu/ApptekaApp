package com.hiaashuu.appteka.screen.details.adapter.security

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class SecurityItemBlueprint(override val presenter: ItemPresenter<SecurityItemView, SecurityItem>) :
    ItemBlueprint<SecurityItemView, SecurityItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.details_block_security,
        creator = { _, view -> SecurityItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is SecurityItem

}