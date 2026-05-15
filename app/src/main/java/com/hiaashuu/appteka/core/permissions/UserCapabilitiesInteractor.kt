package com.hiaashuu.appteka.core.permissions

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.core.permissions.api.UserCapabilitiesResponse
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Single

interface UserCapabilitiesInteractor {

    fun refresh(): Single<UserCapabilitiesResponse>
}

class UserCapabilitiesInteractorImpl(
    private val api: StoreApi,
    private val provider: UserCapabilitiesProvider,
    private val schedulers: SchedulersFactory,
) : UserCapabilitiesInteractor {

    override fun refresh(): Single<UserCapabilitiesResponse> {
        return api.getUserCapabilities()
            .map { it.result }
            .doOnSuccess { provider.setCapabilities(it.capabilities) }
            .subscribeOn(schedulers.io())
    }
}