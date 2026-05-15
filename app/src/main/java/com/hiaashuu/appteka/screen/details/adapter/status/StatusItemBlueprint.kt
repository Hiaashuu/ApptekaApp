package com.hiaashuu.appteka.screen.details.adapter.status

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class StatusItemBlueprint(override val presenter: ItemPresenter<StatusItemView, StatusItem>) :
    ItemBlueprint<StatusItemView, StatusItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.details_block_status,
        creator = { _, view -> StatusItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is StatusItem

}