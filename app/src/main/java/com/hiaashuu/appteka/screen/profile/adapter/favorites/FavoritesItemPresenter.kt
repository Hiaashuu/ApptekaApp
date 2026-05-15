package com.hiaashuu.appteka.screen.profile.adapter.favorites

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.profile.adapter.ItemListener

class FavoritesItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<FavoritesItemView, FavoritesItem> {

    override fun bindView(view: FavoritesItemView, item: FavoritesItem, position: Int) {
        view.setCount(item.count)
        view.setOnClickListener { listener.onFavoritesClick() }
    }

}