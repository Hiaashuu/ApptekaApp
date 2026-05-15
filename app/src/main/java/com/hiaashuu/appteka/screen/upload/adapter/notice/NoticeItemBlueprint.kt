package com.hiaashuu.appteka.screen.upload.adapter.notice

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class NoticeItemBlueprint(override val presenter: ItemPresenter<NoticeItemView, NoticeItem>) :
    ItemBlueprint<NoticeItemView, NoticeItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.upload_block_notice,
        creator = { _, view -> NoticeItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is NoticeItem

}