package com.hiaashuu.appteka.screen.distro.adapter.apk

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class ApkItemBlueprint(override val presenter: ItemPresenter<ApkItemView, ApkItem>) :
    ItemBlueprint<ApkItemView, ApkItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.distro_item,
        creator = { _, view -> ApkItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is ApkItem

}