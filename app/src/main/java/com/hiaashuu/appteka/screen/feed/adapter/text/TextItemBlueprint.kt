package com.hiaashuu.appteka.screen.feed.adapter.text

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.screen.feed.adapter.ItemListener
import com.hiaashuu.appteka.screen.feed.adapter.ReactionsAdapter
import com.hiaashuu.appteka.screen.feed.adapter.ScreenshotsAdapter

class TextItemBlueprint(
    override val presenter: ItemPresenter<TextItemView, TextItem>,
    listener: ItemListener
) : ItemBlueprint<TextItemView, TextItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.feed_item_text,
        creator = { _, view ->
            TextItemViewHolder(view, ScreenshotsAdapter(listener), ReactionsAdapter())
        }
    )

    override fun isRelevantItem(item: Item) = item is TextItem

}