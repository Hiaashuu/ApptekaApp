package com.hiaashuu.appteka.screen.ratings.adapter.rating

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.screen.ratings.RatingsPreferencesProvider

class RatingItemBlueprint(
    override val presenter: ItemPresenter<RatingItemView, RatingItem>,
    private val preferences: RatingsPreferencesProvider,
) :
    ItemBlueprint<RatingItemView, RatingItem> {

    override val viewHolderProvider =
        ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.rating_item,
            creator = { _, view -> RatingItemViewHolder(view, preferences) }
        )

    override fun isRelevantItem(item: Item) = item is RatingItem

}