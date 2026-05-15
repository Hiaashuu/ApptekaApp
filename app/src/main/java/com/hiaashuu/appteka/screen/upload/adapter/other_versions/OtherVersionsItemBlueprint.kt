package com.hiaashuu.appteka.screen.upload.adapter.other_versions

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class OtherVersionsItemBlueprint(override val presenter: ItemPresenter<OtherVersionsItemView, OtherVersionsItem>) :
    ItemBlueprint<OtherVersionsItemView, OtherVersionsItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.upload_block_other_versions,
        creator = { _, view -> OtherVersionsItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is OtherVersionsItem

}