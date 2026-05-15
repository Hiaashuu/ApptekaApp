package com.hiaashuu.appteka.screen.upload.adapter.submit

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class SubmitItemBlueprint(override val presenter: ItemPresenter<SubmitItemView, SubmitItem>) :
    ItemBlueprint<SubmitItemView, SubmitItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.upload_block_submit,
        creator = { _, view -> SubmitItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is SubmitItem

}