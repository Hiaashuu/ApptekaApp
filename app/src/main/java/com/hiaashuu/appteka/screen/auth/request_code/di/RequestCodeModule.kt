package com.hiaashuu.appteka.screen.auth.request_code.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.auth.request_code.RequestCodeInteractor
import com.hiaashuu.appteka.screen.auth.request_code.RequestCodeInteractorImpl
import com.hiaashuu.appteka.screen.auth.request_code.RequestCodePresenter
import com.hiaashuu.appteka.screen.auth.request_code.RequestCodePresenterImpl
import com.hiaashuu.appteka.screen.auth.request_code.RequestCodeResourceProvider
import com.hiaashuu.appteka.screen.auth.request_code.RequestCodeResourceProviderImpl
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Module
import dagger.Provides

@Module
class RequestCodeModule(
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        resourceProvider: RequestCodeResourceProvider,
        interactor: RequestCodeInteractor,
        schedulers: SchedulersFactory
    ): RequestCodePresenter = RequestCodePresenterImpl(
        resourceProvider,
        interactor,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        schedulers: SchedulersFactory
    ): RequestCodeInteractor = RequestCodeInteractorImpl(api, schedulers)

    @Provides
    @PerActivity
    internal fun provideResourceProvider(): RequestCodeResourceProvider = RequestCodeResourceProviderImpl(context.resources)

}