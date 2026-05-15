package com.hiaashuu.appteka.screen.details.adapter.permissions

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class PermissionsItemBlueprint(override val presenter: ItemPresenter<PermissionsItemView, PermissionsItem>) :
    ItemBlueprint<PermissionsItemView, PermissionsItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.details_block_permissions,
        creator = { _, view -> PermissionsItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is PermissionsItem

}