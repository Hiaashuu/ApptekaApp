package com.hiaashuu.appteka.screen.store.adapter.app

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class AppItemBlueprint(override val presenter: ItemPresenter<AppItemView, AppItem>) :
    ItemBlueprint<AppItemView, AppItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.store_item,
        creator = { _, view -> AppItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is AppItem

}