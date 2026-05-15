package com.hiaashuu.appteka.screen.profile.adapter.downloads

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.profile.adapter.ItemListener

class DownloadsItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<DownloadsItemView, DownloadsItem> {

    override fun bindView(view: DownloadsItemView, item: DownloadsItem, position: Int) {
        view.setCount(item.count)
        view.setOnClickListener { listener.onDownloadsClick() }
    }

}