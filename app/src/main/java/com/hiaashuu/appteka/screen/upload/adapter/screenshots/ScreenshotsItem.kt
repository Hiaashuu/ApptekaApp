package com.hiaashuu.appteka.screen.upload.adapter.screenshots

import com.hiaashuu.appteka.util.adapter.Item

data class ScreenshotsItem(
    override val id: Long,
    val items: List<Item>,
) : Item