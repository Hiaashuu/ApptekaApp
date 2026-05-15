package com.hiaashuu.appteka.screen.profile.adapter.downloads

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class DownloadsItemBlueprint(override val presenter: ItemPresenter<DownloadsItemView, DownloadsItem>) :
    ItemBlueprint<DownloadsItemView, DownloadsItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.profile_block_downloads,
        creator = { _, view -> DownloadsItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is DownloadsItem

}