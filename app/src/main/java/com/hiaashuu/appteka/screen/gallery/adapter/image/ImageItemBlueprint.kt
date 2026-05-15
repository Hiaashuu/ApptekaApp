package com.hiaashuu.appteka.screen.gallery.adapter.image

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class ImageItemBlueprint(override val presenter: ItemPresenter<ImageItemView, ImageItem>) :
    ItemBlueprint<ImageItemView, ImageItem> {

    override val viewHolderProvider =
        ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.gallery_image_item,
            creator = { _, view -> ImageItemViewHolder(view) }
        )

    override fun isRelevantItem(item: Item) = item is ImageItem

}