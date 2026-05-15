package com.hiaashuu.appteka.screen.chat.adapter.incoming

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class IncomingMsgItemBlueprint(override val presenter: ItemPresenter<IncomingMsgItemView, IncomingMsgItem>) :
    ItemBlueprint<IncomingMsgItemView, IncomingMsgItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.chat_item_inc_text,
        creator = { _, view -> IncomingMsgItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is IncomingMsgItem

}