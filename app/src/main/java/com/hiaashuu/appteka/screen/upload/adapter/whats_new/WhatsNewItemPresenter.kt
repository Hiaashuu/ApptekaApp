package com.hiaashuu.appteka.screen.upload.adapter.whats_new

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.upload.adapter.ItemListener

class WhatsNewItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<WhatsNewItemView, WhatsNewItem> {

    override fun bindView(view: WhatsNewItemView, item: WhatsNewItem, position: Int) {
        with(view) {
            setText(item.text)
            setOnTextChangedListener { listener.onWhatsNewChanged(it) }
        }
    }

}