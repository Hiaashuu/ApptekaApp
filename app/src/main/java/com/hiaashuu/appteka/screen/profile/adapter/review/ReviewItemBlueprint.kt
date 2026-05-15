package com.hiaashuu.appteka.screen.profile.adapter.review

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class ReviewItemBlueprint(override val presenter: ItemPresenter<ReviewItemView, ReviewItem>) :
    ItemBlueprint<ReviewItemView, ReviewItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.profile_review_item,
        creator = { _, view -> ReviewItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is ReviewItem

}