package com.hiaashuu.appteka.screen.details.adapter.user_rate

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class UserRateItemBlueprint(override val presenter: ItemPresenter<UserRateItemView, UserRateItem>) :
    ItemBlueprint<UserRateItemView, UserRateItem> {

    override val viewHolderProvider =
        ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.details_block_user_rate,
            creator = { _, view -> UserRateItemViewHolder(view) }
        )

    override fun isRelevantItem(item: Item) = item is UserRateItem

}