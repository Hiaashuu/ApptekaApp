package com.hiaashuu.appteka.screen.profile.adapter.app

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.profile.adapter.uploads.AppItemListener

class AppItemPresenter(
    private val listener: AppItemListener,
) : ItemPresenter<AppItemView, AppItem> {

    override fun bindView(view: AppItemView, item: AppItem, position: Int) {
        view.setIcon(item.icon)
        view.setTitle(item.title)
        view.setRating(item.rating.takeIf { it > 0 })
        view.setOnClickListener { listener.onAppClick(item) }
    }

}