package com.hiaashuu.appteka.screen.permissions.adapter.unsafe

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R

class UnsafePermissionItemBlueprint(override val presenter: ItemPresenter<UnsafePermissionItemView, UnsafePermissionItem>) :
    ItemBlueprint<UnsafePermissionItemView, UnsafePermissionItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.permission_unsafe,
        creator = { _, view -> UnsafePermissionItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is UnsafePermissionItem

}