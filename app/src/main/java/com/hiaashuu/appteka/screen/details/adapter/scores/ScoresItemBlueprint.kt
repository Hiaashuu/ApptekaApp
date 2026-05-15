package com.hiaashuu.appteka.screen.details.adapter.scores

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class ScoresItemBlueprint(override val presenter: ItemPresenter<ScoresItemView, ScoresItem>) :
    ItemBlueprint<ScoresItemView, ScoresItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.details_block_scores,
        creator = { _, view -> ScoresItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is ScoresItem

}