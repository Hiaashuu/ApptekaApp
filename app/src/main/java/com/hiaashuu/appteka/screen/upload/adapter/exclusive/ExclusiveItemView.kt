package com.hiaashuu.appteka.screen.upload.adapter.exclusive

import android.view.View
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.google.android.material.materialswitch.MaterialSwitch
import com.hiaashuu.appteka.R

interface ExclusiveItemView : ItemView {

    fun setExclusive(value: Boolean)

    fun setOnExclusiveChangedListener(listener: ((Boolean) -> Unit)?)

}

class ExclusiveItemViewHolder(view: View) : BaseItemViewHolder(view), ExclusiveItemView {

    private val exclusiveSwitch: MaterialSwitch = view.findViewById(R.id.exclusive)

    private var exclusiveChangedListener: ((Boolean) -> Unit)? = null

    init {
        exclusiveSwitch.setOnCheckedChangeListener { _, isChecked ->
            exclusiveChangedListener?.invoke(isChecked)
        }
    }

    override fun setExclusive(value: Boolean) {
        exclusiveSwitch.isChecked = value
    }

    override fun setOnExclusiveChangedListener(listener: ((Boolean) -> Unit)?) {
        this.exclusiveChangedListener = listener
    }

    override fun onUnbind() {
        this.exclusiveChangedListener = null
    }

}