package com.hiaashuu.appteka.screen.profile.adapter.moderation

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class ModerationItemBlueprint(override val presenter: ItemPresenter<ModerationItemView, ModerationItem>) :
    ItemBlueprint<ModerationItemView, ModerationItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.profile_block_moderation,
        creator = { _, view -> ModerationItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is ModerationItem

}