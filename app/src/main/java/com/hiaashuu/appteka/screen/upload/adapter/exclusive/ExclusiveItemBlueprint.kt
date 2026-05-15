package com.hiaashuu.appteka.screen.upload.adapter.exclusive

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class ExclusiveItemBlueprint(override val presenter: ItemPresenter<ExclusiveItemView, ExclusiveItem>) :
    ItemBlueprint<ExclusiveItemView, ExclusiveItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.upload_block_exclusive,
        creator = { _, view -> ExclusiveItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is ExclusiveItem

}