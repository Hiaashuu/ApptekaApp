package com.hiaashuu.appteka.screen.upload.adapter.screen_append

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.upload.adapter.ItemListener

class ScreenAppendItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<ScreenAppendItemView, ScreenAppendItem> {

    override fun bindView(view: ScreenAppendItemView, item: ScreenAppendItem, position: Int) {
        with(view) {
            setOnClickListener { listener.onScreenAppendClick() }
        }
    }

}