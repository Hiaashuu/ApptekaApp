package com.hiaashuu.appteka.screen.unpublish.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.unpublish.UnpublishInteractor
import com.hiaashuu.appteka.screen.unpublish.UnpublishInteractorImpl
import com.hiaashuu.appteka.screen.unpublish.UnpublishPresenter
import com.hiaashuu.appteka.screen.unpublish.UnpublishPresenterImpl
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Module
import dagger.Provides

@Module
class UnpublishModule(
    private val context: Context,
    private val appId: String,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: UnpublishInteractor,
        schedulers: SchedulersFactory
    ): UnpublishPresenter = UnpublishPresenterImpl(appId, interactor, schedulers, state)

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        schedulers: SchedulersFactory
    ): UnpublishInteractor = UnpublishInteractorImpl(api, schedulers)

}