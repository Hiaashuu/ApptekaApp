package com.hiaashuu.appteka.screen.uploads.adapter.app

import android.content.Context
import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.categories.CategoryItem
import com.hiaashuu.appteka.util.bind
import com.hiaashuu.appteka.util.hide
import com.hiaashuu.appteka.util.show
import com.tomclaw.imageloader.util.fetch

interface AppItemView : ItemView {

    fun setIcon(url: String?)

    fun setTitle(title: String)

    fun setVersion(version: String)

    fun setSize(size: String)

    fun setRating(rating: Float?)

    fun setDownloads(downloads: Int)

    fun showBadge()

    fun hideBadge()

    fun showProgress()

    fun hideProgress()

    fun showError()

    fun hideError()

    fun setStatus(status: String?, success: Boolean)

    fun showOpenSourceBadge()

    fun hideOpenSourceBadge()

    fun showAbiIncompatibleBadge()

    fun hideAbiIncompatibleBadge()

    fun setCategory(category: CategoryItem?)

    fun setOnClickListener(listener: (() -> Unit)?)

    fun setOnRetryListener(listener: (() -> Unit)?)

    fun setClickable(clickable: Boolean)

}

class AppItemViewHolder(view: View) : BaseItemViewHolder(view), AppItemView {

    private val context = view.context
    private val icon: ImageView = view.findViewById(R.id.app_icon)
    private val title: TextView = view.findViewById(R.id.app_name)
    private val version: TextView = view.findViewById(R.id.app_version)
    private val size: TextView = view.findViewById(R.id.app_size)
    private val rating: TextView = view.findViewById(R.id.app_rating)
    private val ratingIcon: View = view.findViewById(R.id.rating_icon)
    private val downloads: TextView = view.findViewById(R.id.app_downloads)
    private val openSource: View = view.findViewById(R.id.open_source)
    private val abiIncompatible: View = view.findViewById(R.id.abi_incompatible)
    private val badge: View = view.findViewById(R.id.badge_new)
    private val progress: View = view.findViewById(R.id.item_progress)
    private val statusContainer: View = view.findViewById(R.id.app_badge)
    private val statusIcon: ImageView = view.findViewById(R.id.app_badge_icon)
    private val statusText: TextView = view.findViewById(R.id.app_badge_text)
    private val categoryTitle: TextView = view.findViewById(R.id.app_category)
    private val error: View = view.findViewById(R.id.error_view)
    private val retryButton: View = view.findViewById(R.id.button_retry)

    private var clickListener: (() -> Unit)? = null
    private var retryListener: (() -> Unit)? = null

    init {
        view.setOnClickListener { clickListener?.invoke() }
        retryButton.setOnClickListener { retryListener?.invoke() }
        title.isSelected = true
        version.isSelected = true
        categoryTitle.isSelected = true
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

    override fun showProgress() {
        progress.visibility = VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = GONE
    }

    override fun showError() {
        error.visibility = VISIBLE
    }

    override fun hideError() {
        error.visibility = GONE
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

    override fun setRating(rating: Float?) {
        this.rating.bind(rating?.toString())
        rating?.let { this.ratingIcon.show() } ?: this.ratingIcon.hide()
    }

    override fun setDownloads(downloads: Int) {
        this.downloads.bind(downloads.toString())
    }

    override fun showBadge() {
        badge.show()
    }

    override fun hideBadge() {
        badge.hide()
    }

    override fun setStatus(status: String?, success: Boolean) {
        this.statusText.text = status
        this.statusContainer.visibility = if (status.isNullOrEmpty()) View.GONE else View.VISIBLE

        val isUpdate = status == context.getString(R.string.store_app_update)

        when {
            isUpdate -> {
                this.statusIcon.setImageResource(R.drawable.ic_download_smooth)
                this.statusIcon.setColorFilter(ContextCompat.getColor(context, android.R.color.white))
                this.statusText.setTextColor(ContextCompat.getColor(context, android.R.color.white))
                this.statusContainer.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.primary_color))
            }
            success -> {
                this.statusIcon.setImageResource(R.drawable.ic_pill_ok)
                this.statusIcon.setColorFilter(getAttributedColor(context, com.google.android.material.R.attr.colorOnTertiaryContainer))
                this.statusText.setTextColor(getAttributedColor(context, com.google.android.material.R.attr.colorOnTertiaryContainer))
                this.statusContainer.backgroundTintList = ColorStateList.valueOf(getAttributedColor(context, com.google.android.material.R.attr.colorTertiaryContainer))
            }
            else -> {
                this.statusIcon.setImageResource(R.drawable.ic_pill_fail)
                this.statusIcon.setColorFilter(getAttributedColor(context, com.google.android.material.R.attr.colorOnErrorContainer))
                this.statusText.setTextColor(getAttributedColor(context, com.google.android.material.R.attr.colorOnErrorContainer))
                this.statusContainer.backgroundTintList = ColorStateList.valueOf(getAttributedColor(context, com.google.android.material.R.attr.colorErrorContainer))
            }
        }
    }

    override fun showOpenSourceBadge() {
        this.openSource.show()
    }

    override fun hideOpenSourceBadge() {
        this.openSource.hide()
    }

    override fun showAbiIncompatibleBadge() {
        this.abiIncompatible.show()
    }

    override fun hideAbiIncompatibleBadge() {
        this.abiIncompatible.hide()
    }

    override fun setCategory(category: CategoryItem?) {
        category?.let {
            categoryTitle.text = it.title
            categoryTitle.visibility = View.VISIBLE
            categoryTitle.isSelected = true
        } ?: run {
            categoryTitle.visibility = View.GONE
        }
    }

    override fun setOnClickListener(listener: (() -> Unit)?) {
        this.clickListener = listener
    }

    override fun setClickable(clickable: Boolean) {
        itemView.isClickable = clickable
    }

    override fun setOnRetryListener(listener: (() -> Unit)?) {
        this.retryListener = listener
    }

    override fun onUnbind() {
        this.clickListener = null
        this.retryListener = null
    }

    private fun getAttributedColor(context: Context, attr: Int): Int {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(attr, typedValue, true)
        return typedValue.data
    }
}