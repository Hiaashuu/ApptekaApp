package com.hiaashuu.appteka.screen.ratings.adapter

import com.hiaashuu.appteka.util.adapter.Item

interface ItemListener {

    fun onItemClick(item: Item)

    fun onDeleteClick(item: Item)

    fun onRetryClick(item: Item)

    fun onLoadMore(item: Item)

}