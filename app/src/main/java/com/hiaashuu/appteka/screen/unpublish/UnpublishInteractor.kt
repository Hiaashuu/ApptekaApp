package com.hiaashuu.appteka.screen.unpublish

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.unpublish.api.UnpublishResponse
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable

interface UnpublishInteractor {

    fun unpublish(appId: String, reason: String): Observable<UnpublishResponse>

}

class UnpublishInteractorImpl(
    private val api: StoreApi,
    private val schedulers: SchedulersFactory
) : UnpublishInteractor {

    override fun unpublish(appId: String, reason: String): Observable<UnpublishResponse> {
        return api.unpublish(appId, reason)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}