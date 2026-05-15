package com.hiaashuu.appteka.screen.auth.verify_code

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.auth.verify_code.api.VerifyCodeResponse
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable

interface VerifyCodeInteractor {

    fun verifyCode(requestId: String, code: String, name: String?): Observable<VerifyCodeResponse>

}

class VerifyCodeInteractorImpl(
    private val api: StoreApi,
    private val schedulers: SchedulersFactory
) : VerifyCodeInteractor {

    override fun verifyCode(
        requestId: String,
        code: String,
        name: String?,
    ): Observable<VerifyCodeResponse> {
        return api.verifyCode(requestId, code, name)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}