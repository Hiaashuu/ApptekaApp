package com.hiaashuu.appteka.screen.post.adapter.ribbon

import com.hiaashuu.appteka.util.adapter.Item

data class RibbonItem(
    override val id: Long,
    val items: List<Item>,
) : Item