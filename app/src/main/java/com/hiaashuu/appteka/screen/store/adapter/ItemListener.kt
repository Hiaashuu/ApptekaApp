package com.hiaashuu.appteka.screen.store.adapter

import com.hiaashuu.appteka.util.adapter.Item

interface ItemListener {

    fun onItemClick(item: Item)

    fun onLoadMore(item: Item)

}