package com.hiaashuu.appteka.screen.installed.adapter.app

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.util.bind
import com.hiaashuu.appteka.util.hide
import com.hiaashuu.appteka.util.show
import com.tomclaw.imageloader.util.fetch

interface AppItemView : ItemView {

    fun setIcon(url: String?)

    fun setTitle(title: String)

    fun setVersion(version: String)

    fun setSize(size: String)

    fun showBadge()

    fun hideBadge()

    fun setUpdateTime(time: String)

    fun setUpdatable(value: Boolean)

    fun setOnClickListener(listener: (() -> Unit)?)

    fun setOnUpdateClickListener(listener: (() -> Unit)?)

}

class AppItemViewHolder(view: View) : BaseItemViewHolder(view), AppItemView {

    private val icon: ImageView = view.findViewById(R.id.app_icon)
    private val title: TextView = view.findViewById(R.id.app_name)
    private val version: TextView = view.findViewById(R.id.app_version)
    private val size: TextView = view.findViewById(R.id.app_size)
    private val badge: View = view.findViewById(R.id.badge_new)
    private val updateTime: TextView = view.findViewById(R.id.app_update_time)
    private val updateButton: View = view.findViewById(R.id.update_button)

    private var clickListener: (() -> Unit)? = null
    private var updateClickListener: (() -> Unit)? = null

    init {
        view.setOnClickListener { clickListener?.invoke() }
        updateButton.setOnClickListener { updateClickListener?.invoke() }
        title.isSelected = true
        version.isSelected = true
        updateTime.isSelected = true
    }

    override fun setIcon(url: String?) {
        icon.fetch(url.orEmpty()) {
            centerCrop()
            placeholder(R.drawable.app_placeholder)
            onLoading { imageView ->
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.setImageResource(R.drawable.app_placeholder)
            }
        }
    }

    override fun setTitle(title: String) {
        this.title.text = title
        this.title.visibility = if (title.isEmpty()) View.GONE else View.VISIBLE
        this.title.isSelected = true
    }

    override fun setVersion(version: String) {
        this.version.text = version
        this.version.visibility = if (version.isEmpty()) View.GONE else View.VISIBLE
        this.version.isSelected = true
    }

    override fun setSize(size: String) {
        this.size.bind(size)
    }

    override fun showBadge() {
        badge.show()
    }

    override fun hideBadge() {
        badge.hide()
    }

    override fun setUpdateTime(time: String) {
        this.updateTime.text = time
        this.updateTime.visibility = if (time.isEmpty()) View.GONE else View.VISIBLE
        this.updateTime.isSelected = true
    }

    override fun setUpdatable(value: Boolean) {
        this.updateButton.isVisible = value
    }

    override fun setOnClickListener(listener: (() -> Unit)?) {
        this.clickListener = listener
    }

    override fun setOnUpdateClickListener(listener: (() -> Unit)?) {
        this.updateClickListener = listener
    }

    override fun onUnbind() {
        this.clickListener = null
        this.updateClickListener = null
    }

}