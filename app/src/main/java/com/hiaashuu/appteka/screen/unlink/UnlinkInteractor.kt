package com.hiaashuu.appteka.screen.unlink

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.unlink.api.UnlinkResponse
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable

interface UnlinkInteractor {

    fun unlink(appId: String, reason: String): Observable<UnlinkResponse>

}

class UnlinkInteractorImpl(
    private val api: StoreApi,
    private val schedulers: SchedulersFactory
) : UnlinkInteractor {

    override fun unlink(appId: String, reason: String): Observable<UnlinkResponse> {
        return api.unlink(appId, reason)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}