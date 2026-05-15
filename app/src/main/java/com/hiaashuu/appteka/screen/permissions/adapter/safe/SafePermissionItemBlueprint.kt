package com.hiaashuu.appteka.screen.permissions.adapter.safe

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class SafePermissionItemBlueprint(override val presenter: ItemPresenter<SafePermissionItemView, SafePermissionItem>) :
    ItemBlueprint<SafePermissionItemView, SafePermissionItem> {

    override val viewHolderProvider =
        ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.permission_safe,
            creator = { _, view -> SafePermissionItemViewHolder(view) }
        )

    override fun isRelevantItem(item: Item) = item is SafePermissionItem

}