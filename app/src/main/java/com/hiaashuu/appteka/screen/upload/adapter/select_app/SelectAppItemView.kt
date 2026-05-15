package com.hiaashuu.appteka.screen.upload.adapter.select_app

import android.view.View
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView

interface SelectAppItemView : ItemView {

    fun setOnClickListener(listener: (() -> Unit)?)

}

class SelectAppItemViewHolder(view: View) : BaseItemViewHolder(view), SelectAppItemView {

    private var clickListener: (() -> Unit)? = null

    init {
        view.setOnClickListener { clickListener?.invoke() }
    }

    override fun setOnClickListener(listener: (() -> Unit)?) {
        this.clickListener = listener
    }

    override fun onUnbind() {
        this.clickListener = null
    }

}