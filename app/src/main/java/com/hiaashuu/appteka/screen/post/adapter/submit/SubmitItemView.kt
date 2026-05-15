package com.hiaashuu.appteka.screen.post.adapter.submit

import android.view.View
import android.widget.Button
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.hiaashuu.appteka.R

interface SubmitItemView : ItemView {

    fun setOnClickListener(listener: (() -> Unit)?)

}

class SubmitItemViewHolder(view: View) : BaseItemViewHolder(view), SubmitItemView {

    private val submitButton: Button = view.findViewById(R.id.submit_button)

    private var clickListener: (() -> Unit)? = null

    init {
        submitButton.setOnClickListener { clickListener?.invoke() }
    }

    override fun setOnClickListener(listener: (() -> Unit)?) {
        this.clickListener = listener
    }

    override fun onUnbind() {
        this.clickListener = null
    }

}