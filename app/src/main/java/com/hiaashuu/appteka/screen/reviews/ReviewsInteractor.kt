package com.hiaashuu.appteka.screen.reviews

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.ratings.api.DeleteRatingResponse
import com.hiaashuu.appteka.screen.reviews.api.ReviewEntity
import com.hiaashuu.appteka.user.api.UserBrief
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable
import java.util.Locale

data class UserBriefWrapper(
    val userBrief: UserBrief?
)

interface ReviewsInteractor {

    fun listReviews(offsetRateId: Int?): Observable<List<ReviewEntity>>

    fun getUserBrief(): Observable<UserBriefWrapper>

    fun deleteRating(rateId: Int): Observable<DeleteRatingResponse>

}

class ReviewsInteractorImpl(
    private val api: StoreApi,
    private val userId: Int,
    private val locale: Locale,
    private val schedulers: SchedulersFactory
) : ReviewsInteractor {

    override fun listReviews(offsetRateId: Int?): Observable<List<ReviewEntity>> {
        return api
            .getUserReviews(
                userId = userId,
                rateId = offsetRateId,
                locale = locale.language
            )
            .map { list ->
                list.result.entries
            }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun getUserBrief(): Observable<UserBriefWrapper> {
        return api
            .getUserBrief(userId = null)
            .map { UserBriefWrapper(it.result) }
            .onErrorReturn { UserBriefWrapper(userBrief = null) }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun deleteRating(rateId: Int): Observable<DeleteRatingResponse> {
        return api
            .deleteRating(rateId)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}