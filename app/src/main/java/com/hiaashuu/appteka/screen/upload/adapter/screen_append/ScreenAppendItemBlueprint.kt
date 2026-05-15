package com.hiaashuu.appteka.screen.upload.adapter.screen_append

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class ScreenAppendItemBlueprint(
    override val presenter: ItemPresenter<ScreenAppendItemView, ScreenAppendItem>,
) :
    ItemBlueprint<ScreenAppendItemView, ScreenAppendItem> {

    override val viewHolderProvider =
        ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.upload_block_screenshot_append_item,
            creator = { _, view -> ScreenAppendItemViewHolder(view) }
        )

    override fun isRelevantItem(item: Item) = item is ScreenAppendItem

}