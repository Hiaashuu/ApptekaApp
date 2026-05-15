package com.hiaashuu.appteka.screen.upload.adapter.screen_append

import android.view.View
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.hiaashuu.appteka.R

interface ScreenAppendItemView : ItemView {

    fun setOnClickListener(listener: (() -> Unit)?)

}

class ScreenAppendItemViewHolder(view: View) : BaseItemViewHolder(view), ScreenAppendItemView {

    private val appendScreenButton: View = view.findViewById(R.id.screen_append)

    private var clickListener: (() -> Unit)? = null

    init {
        appendScreenButton.setOnClickListener { clickListener?.invoke() }
    }

    override fun setOnClickListener(listener: (() -> Unit)?) {
        this.clickListener = listener
    }

    override fun onUnbind() {
        this.clickListener = null
    }

}