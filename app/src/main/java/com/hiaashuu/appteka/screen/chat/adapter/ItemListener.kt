package com.hiaashuu.appteka.screen.chat.adapter

import com.hiaashuu.appteka.util.adapter.Item

interface ItemListener {

    fun onItemClick(item: Item)

    fun onLoadMore(msgId: Int)

    fun onAttachmentClick(item: Item, index: Int)

}