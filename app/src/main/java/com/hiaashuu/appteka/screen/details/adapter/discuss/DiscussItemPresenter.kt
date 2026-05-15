package com.hiaashuu.appteka.screen.details.adapter.discuss

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.ItemListener

class DiscussItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<DiscussItemView, DiscussItem> {

    override fun bindView(view: DiscussItemView, item: DiscussItem, position: Int) {
        item.msgCount?.let { view.showMsgCount(it) } ?: view.showNoMsgIndicator()
        view.setOnDiscussClickListener {
            listener.onDiscussClick()
        }
    }

}