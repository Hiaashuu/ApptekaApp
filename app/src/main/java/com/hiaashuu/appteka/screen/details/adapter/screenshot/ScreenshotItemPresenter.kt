package com.hiaashuu.appteka.screen.details.adapter.screenshot

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.screenshots.ScreenshotItemListener

class ScreenshotItemPresenter(
    private val listener: ScreenshotItemListener,
) : ItemPresenter<ScreenshotItemView, ScreenshotItem> {

    override fun bindView(view: ScreenshotItemView, item: ScreenshotItem, position: Int) {
        with(view) {
            setImage(item)
            setOnClickListener { listener.onScreenshotClick(position) }
        }
    }

}