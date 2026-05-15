package com.hiaashuu.appteka.screen.upload.adapter.prefill_version

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class PrefillVersionItemBlueprint(
    override val presenter: ItemPresenter<PrefillVersionItemView, PrefillVersionItem>
) : ItemBlueprint<PrefillVersionItemView, PrefillVersionItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.upload_block_prefill_version,
        creator = { _, view -> PrefillVersionItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is PrefillVersionItem

}