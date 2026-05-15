package com.hiaashuu.appteka.screen.upload.adapter.open_source

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.upload.adapter.ItemListener

class OpenSourceItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<OpenSourceItemView, OpenSourceItem> {

    override fun bindView(view: OpenSourceItemView, item: OpenSourceItem, position: Int) {
        with(view) {
            setOpenSource(item.value)
            setUrl(item.url)
            setUrlVisible(item.value)
            setOnOpenSourceChangedListener { value, url ->
                listener.onOpenSourceChanged(value, url)
                setUrlVisible(value)
            }
        }
    }

}