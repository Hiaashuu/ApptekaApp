package com.hiaashuu.appteka.screen.upload.adapter.exclusive

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.upload.adapter.ItemListener

class ExclusiveItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<ExclusiveItemView, ExclusiveItem> {

    override fun bindView(view: ExclusiveItemView, item: ExclusiveItem, position: Int) {
        with(view) {
            setExclusive(item.value)
            setOnExclusiveChangedListener { listener.onExclusiveChanged(it) }
        }
    }

}