package com.hiaashuu.appteka.screen.gallery.adapter.image

import com.hiaashuu.appteka.util.adapter.ItemPresenter

class ImageItemPresenter : ItemPresenter<ImageItemView, ImageItem> {

    override fun bindView(view: ImageItemView, item: ImageItem, position: Int) {
        view.setUri(item.uri)
    }

}