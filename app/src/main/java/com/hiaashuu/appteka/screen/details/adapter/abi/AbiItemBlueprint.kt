package com.hiaashuu.appteka.screen.details.adapter.abi

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class AbiItemBlueprint(override val presenter: ItemPresenter<AbiItemView, AbiItem>) :
    ItemBlueprint<AbiItemView, AbiItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.details_block_abi,
        creator = { _, view -> AbiItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is AbiItem

}