package com.hiaashuu.appteka.screen.post.adapter.submit

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.post.adapter.ItemListener

class SubmitItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<SubmitItemView, SubmitItem> {

    override fun bindView(view: SubmitItemView, item: SubmitItem, position: Int) {
        with(view) {
            setOnClickListener { listener.onSubmitClick() }
        }
    }

}