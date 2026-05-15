package com.hiaashuu.appteka.screen.upload.adapter.category

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class SelectCategoryItemBlueprint(override val presenter: ItemPresenter<SelectCategoryItemView, SelectCategoryItem>) :
    ItemBlueprint<SelectCategoryItemView, SelectCategoryItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.upload_block_select_category,
        creator = { _, view -> SelectCategoryItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is SelectCategoryItem

}