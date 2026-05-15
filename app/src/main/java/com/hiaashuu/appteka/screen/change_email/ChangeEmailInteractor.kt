package com.hiaashuu.appteka.screen.change_email

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.auth.request_code.api.RequestCodeResponse
import com.hiaashuu.appteka.screen.auth.verify_code.api.VerifyCodeResponse
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable

interface ChangeEmailInteractor {

    fun requestEmailChange(email: String): Observable<RequestCodeResponse>

    fun verifyEmailChange(requestId: String, code: String): Observable<VerifyCodeResponse>

}

class ChangeEmailInteractorImpl(
    private val api: StoreApi,
    private val schedulers: SchedulersFactory
) : ChangeEmailInteractor {

    override fun requestEmailChange(email: String): Observable<RequestCodeResponse> {
        return api.requestEmailChange(email)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun verifyEmailChange(
        requestId: String,
        code: String
    ): Observable<VerifyCodeResponse> {
        return api.verifyEmailChange(requestId, code)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}