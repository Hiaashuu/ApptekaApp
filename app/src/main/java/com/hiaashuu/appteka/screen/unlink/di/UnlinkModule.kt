package com.hiaashuu.appteka.screen.unlink.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.unlink.UnlinkInteractor
import com.hiaashuu.appteka.screen.unlink.UnlinkInteractorImpl
import com.hiaashuu.appteka.screen.unlink.UnlinkPresenter
import com.hiaashuu.appteka.screen.unlink.UnlinkPresenterImpl
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Module
import dagger.Provides

@Module
class UnlinkModule(
    private val context: Context,
    private val appId: String,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: UnlinkInteractor,
        schedulers: SchedulersFactory
    ): UnlinkPresenter = UnlinkPresenterImpl(appId, interactor, schedulers, state)

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        schedulers: SchedulersFactory
    ): UnlinkInteractor = UnlinkInteractorImpl(api, schedulers)

}