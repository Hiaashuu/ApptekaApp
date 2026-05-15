package com.hiaashuu.appteka.screen.profile.adapter.favorites

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class FavoritesItemBlueprint(override val presenter: ItemPresenter<FavoritesItemView, FavoritesItem>) :
    ItemBlueprint<FavoritesItemView, FavoritesItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.profile_block_favorites,
        creator = { _, view -> FavoritesItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is FavoritesItem

}