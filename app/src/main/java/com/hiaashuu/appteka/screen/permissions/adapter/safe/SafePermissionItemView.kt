package com.hiaashuu.appteka.screen.permissions.adapter.safe

import android.view.View
import android.widget.TextView
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.util.bind

interface SafePermissionItemView : ItemView {

    fun setDescription(value: String?)

    fun setPermission(value: String)

}

class SafePermissionItemViewHolder(view: View) : BaseItemViewHolder(view), SafePermissionItemView {

    private val description: TextView = view.findViewById(R.id.description)
    private val permission: TextView = view.findViewById(R.id.permission)

    override fun setDescription(value: String?) {
        description.bind(value)
    }

    override fun setPermission(value: String) {
        permission.bind(value)
    }

}