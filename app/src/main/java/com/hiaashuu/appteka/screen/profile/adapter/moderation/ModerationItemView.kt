package com.hiaashuu.appteka.screen.profile.adapter.moderation

import android.view.View
import android.widget.TextView
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.util.bind
import com.hiaashuu.appteka.util.hide
import com.hiaashuu.appteka.util.show

interface ModerationItemView : ItemView {

    fun setCount(count: Int)

    fun setNoApps()

    fun showIndicator()

    fun hideIndicator()

    fun setOnClickListener(listener: (() -> Unit)?)

}

class ModerationItemViewHolder(view: View) : BaseItemViewHolder(view), ModerationItemView {

    private val context = view.context
    private val subtitle: TextView = view.findViewById(R.id.subtitle)
    private val indicator: View = view.findViewById(R.id.indicator)

    private var clickListener: (() -> Unit)? = null

    init {
        view.setOnClickListener { clickListener?.invoke() }
    }

    override fun setCount(count: Int) {
        this.subtitle.bind(
            context.resources.getQuantityString(
                R.plurals.moderation_apps_count,
                count,
                count
            )
        )
    }

    override fun setNoApps() {
        this.subtitle.bind(context.getString(R.string.no_apps_on_moderation))
    }

    override fun showIndicator() {
        indicator.show()
    }

    override fun hideIndicator() {
        indicator.hide()
    }

    override fun setOnClickListener(listener: (() -> Unit)?) {
        this.clickListener = listener
    }

    override fun onUnbind() {
        this.clickListener = null
    }

}