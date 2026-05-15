package com.hiaashuu.appteka.screen.auth.request_code

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.auth.request_code.api.RequestCodeResponse
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable

interface RequestCodeInteractor {

    fun requestCode(email: String): Observable<RequestCodeResponse>

}

class RequestCodeInteractorImpl(
    private val api: StoreApi,
    private val schedulers: SchedulersFactory
) : RequestCodeInteractor {

    override fun requestCode(
        email: String,
    ): Observable<RequestCodeResponse> {
        return api.requestCode(email)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}