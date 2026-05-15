package com.hiaashuu.appteka.screen.post.adapter.image

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.post.adapter.ItemListener

class ImageItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<ImageItemView, ImageItem> {

    override fun bindView(view: ImageItemView, item: ImageItem, position: Int) {
        with(view) {
            setImage(item)
            setRemote(item.remote)
            setOnClickListener { listener.onImageClick(item) }
            setOnDeleteListener { listener.onImageDelete(item) }
        }
    }

}