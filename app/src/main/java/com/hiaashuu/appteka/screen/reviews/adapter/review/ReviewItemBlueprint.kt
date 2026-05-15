package com.hiaashuu.appteka.screen.reviews.adapter.review

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.screen.reviews.ReviewsPreferencesProvider

class ReviewItemBlueprint(
    override val presenter: ItemPresenter<ReviewItemView, ReviewItem>,
    private val preferences: ReviewsPreferencesProvider,
) :
    ItemBlueprint<ReviewItemView, ReviewItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.review_item,
        creator = { _, view -> ReviewItemViewHolder(view, preferences) }
    )

    override fun isRelevantItem(item: Item) = item is ReviewItem

}