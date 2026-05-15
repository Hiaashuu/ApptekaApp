package com.hiaashuu.appteka.screen.post.adapter.text

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class TextItemBlueprint(override val presenter: ItemPresenter<TextItemView, TextItem>) :
    ItemBlueprint<TextItemView, TextItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.post_block_text,
        creator = { _, view -> TextItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is TextItem

}