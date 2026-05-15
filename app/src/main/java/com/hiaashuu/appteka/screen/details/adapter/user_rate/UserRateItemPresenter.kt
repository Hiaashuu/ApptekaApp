package com.hiaashuu.appteka.screen.details.adapter.user_rate

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.ItemListener
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Completable
import java.util.concurrent.TimeUnit

class UserRateItemPresenter(
    private val listener: ItemListener,
    private val schedulers: SchedulersFactory,
) : ItemPresenter<UserRateItemView, UserRateItem> {

    override fun bindView(view: UserRateItemView, item: UserRateItem, position: Int) {

        val capability = item.rateCapability
        val denied = capability != null && !capability.allowed
        view.setRatingEditable(!denied)
        view.setFeedbackEnabled(!denied)
        view.setDenialBanner(if (denied) capability else null)
        view.setOnRateListener { rating ->
            listener.onRateClick(rating, review = null)
            Completable
                .timer(1, TimeUnit.SECONDS, schedulers.mainThread())
                .subscribe { view.setRating(0f) }
        }
    }

}