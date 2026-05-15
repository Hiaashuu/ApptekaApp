package com.hiaashuu.appteka.screen.moderation.adapter

import com.hiaashuu.appteka.util.adapter.Item

interface ItemListener {

    fun onItemClick(item: Item)

    fun onRetryClick(item: Item)

    fun onLoadMore(item: Item)

}