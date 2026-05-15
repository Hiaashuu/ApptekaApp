package com.hiaashuu.appteka.screen.topics.adapter

import com.hiaashuu.appteka.util.adapter.Item

interface ItemListener {

    fun onItemClick(item: Item)

    fun onItemLongClick(item: Item)

    fun onLoadMore(item: Item)

}