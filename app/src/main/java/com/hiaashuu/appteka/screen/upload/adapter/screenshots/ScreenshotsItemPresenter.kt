package com.hiaashuu.appteka.screen.upload.adapter.screenshots

import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.upload.adapter.ItemListener
import dagger.Lazy

class ScreenshotsItemPresenter(
    private val listener: ItemListener,
    private val adapterPresenter: Lazy<AdapterPresenter>,
) : ItemPresenter<ScreenshotsItemView, ScreenshotsItem> {

    override fun bindView(view: ScreenshotsItemView, item: ScreenshotsItem, position: Int) {
        adapterPresenter.get().onDataSourceChanged(item.items)
        view.notifyChanged()
    }

}