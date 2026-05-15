package com.hiaashuu.appteka.screen.profile.adapter.favorites

import android.view.View
import android.widget.TextView
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.util.bind

interface FavoritesItemView : ItemView {

    fun setCount(count: Int)

    fun setOnClickListener(listener: (() -> Unit)?)

}

class FavoritesItemViewHolder(view: View) : BaseItemViewHolder(view), FavoritesItemView {

    private val context = view.context
    private val subtitle: TextView = view.findViewById(R.id.subtitle)

    private var clickListener: (() -> Unit)? = null

    init {
        view.setOnClickListener { clickListener?.invoke() }
    }

    override fun setCount(count: Int) {
        this.subtitle.bind(
            context.resources.getQuantityString(
                R.plurals.favorite_apps_count,
                count,
                count
            )
        )
    }

    override fun setOnClickListener(listener: (() -> Unit)?) {
        this.clickListener = listener
    }

    override fun onUnbind() {
        this.clickListener = null
    }

}