package com.hiaashuu.appteka.screen.upload.adapter.select_app

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.upload.adapter.ItemListener

class SelectAppItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<SelectAppItemView, SelectAppItem> {

    override fun bindView(view: SelectAppItemView, item: SelectAppItem, position: Int) {
        view.setOnClickListener { listener.onSelectAppClick() }
    }

}