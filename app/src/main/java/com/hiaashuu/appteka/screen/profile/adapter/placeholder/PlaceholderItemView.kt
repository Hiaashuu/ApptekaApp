package com.hiaashuu.appteka.screen.profile.adapter.placeholder

import android.view.View
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView

interface PlaceholderItemView : ItemView

class PlaceholderItemViewHolder(view: View) : BaseItemViewHolder(view), PlaceholderItemView {

    override fun onUnbind() {}

}