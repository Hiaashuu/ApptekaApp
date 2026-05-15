package com.hiaashuu.appteka.screen.post.adapter.append

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.post.adapter.ItemListener

class AppendItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<AppendItemView, AppendItem> {

    override fun bindView(view: AppendItemView, item: AppendItem, position: Int) {
        with(view) {
            setOnClickListener { listener.onScreenAppendClick() }
        }
    }

}