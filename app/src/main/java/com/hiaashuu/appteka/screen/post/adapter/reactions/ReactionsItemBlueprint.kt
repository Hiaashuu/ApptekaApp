package com.hiaashuu.appteka.screen.post.adapter.reactions

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.screen.feed.adapter.ReactionsAdapter

class ReactionsItemBlueprint(
    override val presenter: ItemPresenter<ReactionsItemView, ReactionsItem>
) : ItemBlueprint<ReactionsItemView, ReactionsItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.post_block_reactions,
        creator = { _, view ->
            val reactionsAdapter = ReactionsAdapter()
            ReactionsItemViewHolder(view, reactionsAdapter)
        }
    )

    override fun isRelevantItem(item: Item) = item is ReactionsItem

}