package com.hiaashuu.appteka.screen.details.adapter.play

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class PlayItemBlueprint(override val presenter: ItemPresenter<PlayItemView, PlayItem>) :
    ItemBlueprint<PlayItemView, PlayItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.details_block_play,
        creator = { _, view -> PlayItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is PlayItem

}