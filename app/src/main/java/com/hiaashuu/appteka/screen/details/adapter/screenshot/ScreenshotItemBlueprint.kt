package com.hiaashuu.appteka.screen.details.adapter.screenshot

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class ScreenshotItemBlueprint(
    override val presenter: ItemPresenter<ScreenshotItemView, ScreenshotItem>,
) :
    ItemBlueprint<ScreenshotItemView, ScreenshotItem> {

    override val viewHolderProvider =
        ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.details_block_screenshot_item,
            creator = { _, view -> ScreenshotItemViewHolder(view) }
        )

    override fun isRelevantItem(item: Item) = item is ScreenshotItem

}