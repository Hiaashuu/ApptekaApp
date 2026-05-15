package com.hiaashuu.appteka.screen.feed.adapter.upload

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.screen.feed.adapter.ItemListener
import com.hiaashuu.appteka.screen.feed.adapter.ReactionsAdapter
import com.hiaashuu.appteka.screen.feed.adapter.ScreenshotsAdapter

class UploadItemBlueprint(
    override val presenter: ItemPresenter<UploadItemView, UploadItem>,
    listener: ItemListener
) : ItemBlueprint<UploadItemView, UploadItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.feed_item_upload,
        creator = { _, view ->
            UploadItemViewHolder(view, ScreenshotsAdapter(listener), ReactionsAdapter())
        }
    )

    override fun isRelevantItem(item: Item) = item is UploadItem

}