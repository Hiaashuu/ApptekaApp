package com.hiaashuu.appteka.screen.upload.adapter.open_source

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class OpenSourceItemBlueprint(override val presenter: ItemPresenter<OpenSourceItemView, OpenSourceItem>) :
    ItemBlueprint<OpenSourceItemView, OpenSourceItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.upload_block_open_source,
        creator = { _, view -> OpenSourceItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is OpenSourceItem

}