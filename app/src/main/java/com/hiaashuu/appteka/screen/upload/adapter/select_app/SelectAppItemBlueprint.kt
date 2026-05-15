package com.hiaashuu.appteka.screen.upload.adapter.select_app

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class SelectAppItemBlueprint(override val presenter: ItemPresenter<SelectAppItemView, SelectAppItem>) :
    ItemBlueprint<SelectAppItemView, SelectAppItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.upload_block_select_app,
        creator = { _, view -> SelectAppItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is SelectAppItem

}