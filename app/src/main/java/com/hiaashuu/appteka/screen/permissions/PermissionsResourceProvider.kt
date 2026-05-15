package com.hiaashuu.appteka.screen.permissions

import android.content.res.Resources
import com.hiaashuu.appteka.R

interface PermissionsResourceProvider {

    fun getUnknownPermissionString(): String
}

class PermissionsResourceProviderImpl(
    private val resources: Resources
) : PermissionsResourceProvider {

    override fun getUnknownPermissionString(): String {
        return resources.getString(R.string.unknown_permission_description)
    }

}