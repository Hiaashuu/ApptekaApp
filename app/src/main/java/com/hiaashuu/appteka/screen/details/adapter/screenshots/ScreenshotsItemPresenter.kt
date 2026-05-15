package com.hiaashuu.appteka.screen.details.adapter.screenshots

import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.ItemListener
import com.hiaashuu.appteka.screen.details.adapter.screenshot.ScreenshotItem

class ScreenshotsItemPresenter(
    private val listener: ItemListener,
    private val adapterPresenter: dagger.Lazy<AdapterPresenter>,
) : ItemPresenter<ScreenshotsItemView, ScreenshotsItem>, ScreenshotItemListener {

    private var screenshots = emptyList<ScreenshotItem>()

    override fun bindView(view: ScreenshotsItemView, item: ScreenshotsItem, position: Int) {
        screenshots = item.items
        adapterPresenter.get().onDataSourceChanged(item.items)
        view.notifyChanged()
    }

    override fun onScreenshotClick(position: Int) {
        listener.onScreenshotClick(screenshots, position)
    }

}