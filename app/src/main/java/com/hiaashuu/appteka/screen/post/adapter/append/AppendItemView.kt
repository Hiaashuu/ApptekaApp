package com.hiaashuu.appteka.screen.post.adapter.append

import android.view.View
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.hiaashuu.appteka.R

interface AppendItemView : ItemView {

    fun setOnClickListener(listener: (() -> Unit)?)

}

class AppendItemViewHolder(view: View) : BaseItemViewHolder(view), AppendItemView {

    private val appendScreenButton: View = view.findViewById(R.id.image_append)

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