package com.hiaashuu.appteka.screen.installed.adapter

import com.hiaashuu.appteka.util.adapter.Item

interface ItemListener {

    fun onItemClick(item: Item)

    fun onUpdateClick(title: String, appId: String)

}