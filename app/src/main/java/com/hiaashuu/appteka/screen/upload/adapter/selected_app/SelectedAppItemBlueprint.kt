package com.hiaashuu.appteka.screen.upload.adapter.selected_app

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class SelectedAppItemBlueprint(override val presenter: ItemPresenter<SelectedAppItemView, SelectedAppItem>) :
    ItemBlueprint<SelectedAppItemView, SelectedAppItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.upload_block_selected_app,
        creator = { _, view -> SelectedAppItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is SelectedAppItem

}