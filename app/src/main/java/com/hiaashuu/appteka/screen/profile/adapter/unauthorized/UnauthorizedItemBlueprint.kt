package com.hiaashuu.appteka.screen.profile.adapter.unauthorized

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class UnauthorizedItemBlueprint(override val presenter: ItemPresenter<UnauthorizedItemView, UnauthorizedItem>) :
    ItemBlueprint<UnauthorizedItemView, UnauthorizedItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.profile_block_unauthorized,
        creator = { _, view -> UnauthorizedItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is UnauthorizedItem

}