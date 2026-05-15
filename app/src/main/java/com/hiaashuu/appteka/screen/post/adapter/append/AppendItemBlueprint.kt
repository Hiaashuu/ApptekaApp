package com.hiaashuu.appteka.screen.post.adapter.append

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class AppendItemBlueprint(
    override val presenter: ItemPresenter<AppendItemView, AppendItem>,
) :
    ItemBlueprint<AppendItemView, AppendItem> {

    override val viewHolderProvider =
        ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.post_block_append_item,
            creator = { _, view -> AppendItemViewHolder(view) }
        )

    override fun isRelevantItem(item: Item) = item is AppendItem

}