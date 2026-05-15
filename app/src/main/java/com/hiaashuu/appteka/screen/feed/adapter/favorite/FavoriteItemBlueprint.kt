package com.hiaashuu.appteka.screen.feed.adapter.favorite

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.screen.feed.adapter.ItemListener
import com.hiaashuu.appteka.screen.feed.adapter.ReactionsAdapter
import com.hiaashuu.appteka.screen.feed.adapter.ScreenshotsAdapter

class FavoriteItemBlueprint(
    override val presenter: ItemPresenter<FavoriteItemView, FavoriteItem>,
    listener: ItemListener
) : ItemBlueprint<FavoriteItemView, FavoriteItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.feed_item_favorite,
        creator = { _, view ->
            FavoriteItemViewHolder(view, ScreenshotsAdapter(listener), ReactionsAdapter())
        }
    )

    override fun isRelevantItem(item: Item) = item is FavoriteItem

}