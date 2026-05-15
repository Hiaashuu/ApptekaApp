package com.hiaashuu.appteka.screen.chat.adapter.system

import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder

class SystemMsgItemBlueprint(override val presenter: ItemPresenter<SystemMsgItemView, SystemMsgItem>) :
    ItemBlueprint<SystemMsgItemView, SystemMsgItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.chat_item_system,
        creator = { _, view -> SystemMsgItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is SystemMsgItem

}