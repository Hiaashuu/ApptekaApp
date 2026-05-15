package com.hiaashuu.appteka.screen.home.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.core.AppInfoProvider
import com.hiaashuu.appteka.core.StandByApi
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.core.permissions.UserCapabilitiesInteractor
import com.hiaashuu.appteka.core.permissions.UserCapabilitiesProvider
import com.hiaashuu.appteka.screen.home.HomeInteractor
import com.hiaashuu.appteka.screen.home.HomeInteractorImpl
import com.hiaashuu.appteka.screen.home.HomePresenter
import com.hiaashuu.appteka.screen.home.HomePresenterImpl
import com.hiaashuu.appteka.user.ModerationProvider
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import com.tomclaw.bananalytics.Bananalytics
import dagger.Module
import dagger.Provides
import java.util.Locale

@Module
class HomeModule(
    private val context: Context,
    private val startAction: String?,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        bananalytics: Bananalytics,
        interactor: HomeInteractor,
        moderationProvider: ModerationProvider,
        capabilitiesProvider: UserCapabilitiesProvider,
        capabilitiesInteractor: UserCapabilitiesInteractor,
        schedulers: SchedulersFactory
    ): HomePresenter = HomePresenterImpl(
        startAction,
        bananalytics,
        interactor,
        moderationProvider,
        capabilitiesProvider,
        capabilitiesInteractor,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        storeApi: StoreApi,
        standByApi: StandByApi,
        locale: Locale,
        appInfoProvider: AppInfoProvider,
        schedulers: SchedulersFactory
    ): HomeInteractor = HomeInteractorImpl(
        storeApi,
        standByApi,
        locale,
        appInfoProvider,
        schedulers
    )

}