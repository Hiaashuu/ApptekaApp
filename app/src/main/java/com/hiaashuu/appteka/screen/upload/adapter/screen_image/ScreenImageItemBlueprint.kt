package com.hiaashuu.appteka.screen.upload.adapter.screen_image

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class ScreenImageItemBlueprint(
    override val presenter: ItemPresenter<ScreenImageItemView, ScreenImageItem>,
) :
    ItemBlueprint<ScreenImageItemView, ScreenImageItem> {

    override val viewHolderProvider =
        ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.upload_block_screenshot_image_item,
            creator = { _, view -> ScreenImageItemViewHolder(view) }
        )

    override fun isRelevantItem(item: Item) = item is ScreenImageItem

}