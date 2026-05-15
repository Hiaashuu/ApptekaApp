package com.hiaashuu.appteka.screen.home

import com.hiaashuu.appteka.core.AppInfoProvider
import com.hiaashuu.appteka.core.StandByApi
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.home.api.StartupResponse
import com.hiaashuu.appteka.screen.home.api.StatusResponse
import com.hiaashuu.appteka.user.api.UserBrief
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable
import java.util.Locale

interface HomeInteractor {

    fun loadStartup(): Observable<StartupResponse>

    fun loadStatus(): Observable<StatusResponse>

    fun getUserBrief(): Observable<UserBrief>

}

class HomeInteractorImpl(
    private val storeApi: StoreApi,
    private val standByApi: StandByApi,
    private val locale: Locale,
    private val appInfoProvider: AppInfoProvider,
    private val schedulers: SchedulersFactory
) : HomeInteractor {

    override fun loadStartup(): Observable<StartupResponse> {
        return storeApi
            .getStartup()
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun loadStatus(): Observable<StatusResponse> {
        return standByApi
            .getStatus(locale.language, appInfoProvider.getVersionCode())
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun getUserBrief(): Observable<UserBrief> {
        return storeApi
            .getUserBrief(userId = null)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}