package com.hiaashuu.appteka.screen.details.adapter.whats_new

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.ItemListener

class WhatsNewItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<WhatsNewItemView, WhatsNewItem> {

    override fun bindView(view: WhatsNewItemView, item: WhatsNewItem, position: Int) {
        view.setText(item.text)
    }

}