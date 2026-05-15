package com.hiaashuu.appteka.screen.details.adapter.whats_new

import android.view.View
import android.widget.TextView
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.util.bind

interface WhatsNewItemView : ItemView {

    fun setText(value: String)

}

class WhatsNewItemViewHolder(view: View) : BaseItemViewHolder(view), WhatsNewItemView {

    private val context = view.context
    private val whatsNew: TextView = view.findViewById(R.id.whats_new)

    override fun setText(value: String) {
        whatsNew.bind(value)
    }

}