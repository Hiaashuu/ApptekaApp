package com.hiaashuu.appteka.screen.permissions.adapter.safe

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.permissions.PermissionsResourceProvider

class SafePermissionItemPresenter(
    private val resourceProvider: PermissionsResourceProvider
) : ItemPresenter<SafePermissionItemView, SafePermissionItem> {

    override fun bindView(view: SafePermissionItemView, item: SafePermissionItem, position: Int) {
        val description = item.description ?: resourceProvider.getUnknownPermissionString()
        view.setDescription(description)
        view.setPermission(item.permission)
    }

}