package com.hiaashuu.appteka.screen.chat.adapter.outgoing

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class OutgoingMsgItemBlueprint(override val presenter: ItemPresenter<OutgoingMsgItemView, OutgoingMsgItem>) :
    ItemBlueprint<OutgoingMsgItemView, OutgoingMsgItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.chat_item_out_text,
        creator = { _, view -> OutgoingMsgItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is OutgoingMsgItem

}