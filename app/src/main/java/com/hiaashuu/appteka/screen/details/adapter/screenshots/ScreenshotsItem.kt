package com.hiaashuu.appteka.screen.details.adapter.screenshots

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.screen.details.adapter.screenshot.ScreenshotItem

data class ScreenshotsItem(
    override val id: Long,
    val items: List<ScreenshotItem>,
) : Item