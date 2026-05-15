package com.hiaashuu.appteka.screen.details.adapter.rating

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class RatingItemBlueprint(override val presenter: ItemPresenter<RatingItemView, RatingItem>) :
    ItemBlueprint<RatingItemView, RatingItem> {

    override val viewHolderProvider =
        ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.details_block_rating,
            creator = { _, view -> RatingItemViewHolder(view) }
        )

    override fun isRelevantItem(item: Item) = item is RatingItem

}