package com.hiaashuu.appteka.screen.details.adapter.user_review

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class UserReviewItemBlueprint(override val presenter: ItemPresenter<UserReviewItemView, UserReviewItem>) :
    ItemBlueprint<UserReviewItemView, UserReviewItem> {

    override val viewHolderProvider =
        ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.details_block_review,
            creator = { _, view -> UserReviewItemViewHolder(view) }
        )

    override fun isRelevantItem(item: Item) = item is UserReviewItem

}