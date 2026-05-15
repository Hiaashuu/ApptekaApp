package com.hiaashuu.appteka.screen.rate

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.rate.api.SubmitReviewResponse
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable

interface RateInteractor {

    fun submitReview(appId: String, rating: Float, review: String): Observable<SubmitReviewResponse>

}

class RateInteractorImpl(
    private val api: StoreApi,
    private val schedulers: SchedulersFactory
) : RateInteractor {

    override fun submitReview(
        appId: String,
        rating: Float,
        review: String
    ): Observable<SubmitReviewResponse> {
        return api
            .submitReview(
                appId = appId,
                score = rating.toInt(),
                text = review,
            )
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}