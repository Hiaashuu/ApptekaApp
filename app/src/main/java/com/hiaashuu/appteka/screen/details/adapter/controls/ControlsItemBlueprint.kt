package com.hiaashuu.appteka.screen.details.adapter.controls

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class ControlsItemBlueprint(override val presenter: ItemPresenter<ControlsItemView, ControlsItem>) :
    ItemBlueprint<ControlsItemView, ControlsItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.details_block_controls,
        creator = { _, view -> ControlsItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is ControlsItem

}