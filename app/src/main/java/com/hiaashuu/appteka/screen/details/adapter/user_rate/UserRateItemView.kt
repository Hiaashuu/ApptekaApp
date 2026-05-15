package com.hiaashuu.appteka.screen.details.adapter.user_rate

import android.view.View
import android.widget.RatingBar
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.core.permissions.Capability
import com.hiaashuu.appteka.uikit.permissions.PermissionBanner
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView

interface UserRateItemView : ItemView {

    fun setRating(value: Float)

    fun setOnRateListener(listener: ((Float) -> Unit)?)

    fun setRatingEditable(editable: Boolean)

    fun setFeedbackEnabled(enabled: Boolean)

    fun setDenialBanner(capability: Capability?)

}

class UserRateItemViewHolder(view: View) : BaseItemViewHolder(view), UserRateItemView {

    private val ratingView: RatingBar = view.findViewById(R.id.rating_view)
    private val feedbackButton: View = view.findViewById(R.id.feedback_button)
    private val banner: PermissionBanner = view.findViewById(R.id.rate_permission_banner)

    private var rateListener: ((Float) -> Unit)? = null

    init {
        feedbackButton.setOnClickListener { rateListener?.invoke(ratingView.rating) }
        ratingView.setOnRatingBarChangeListener { _, rating, fromUser ->
            if (fromUser) {
                rateListener?.invoke(rating)
            }
        }
    }

    override fun setRating(value: Float) {
        ratingView.rating = value
    }

    override fun setOnRateListener(listener: ((Float) -> Unit)?) {
        this.rateListener = listener
    }

    override fun setRatingEditable(editable: Boolean) {
        ratingView.setIsIndicator(!editable)
    }

    override fun setFeedbackEnabled(enabled: Boolean) {
        feedbackButton.isEnabled = enabled
    }

    override fun setDenialBanner(capability: Capability?) {
        if (capability == null) {
            banner.hide()
        } else {
            banner.showFor(capability)
        }
    }

    override fun onUnbind() {
        this.rateListener = null
    }

}