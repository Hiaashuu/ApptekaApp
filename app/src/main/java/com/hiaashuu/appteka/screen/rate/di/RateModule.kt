package com.hiaashuu.appteka.screen.rate.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.rate.RateInteractor
import com.hiaashuu.appteka.screen.rate.RateInteractorImpl
import com.hiaashuu.appteka.screen.rate.RatePresenter
import com.hiaashuu.appteka.screen.rate.RatePresenterImpl
import com.hiaashuu.appteka.screen.rate.RateResourceProvider
import com.hiaashuu.appteka.screen.rate.RateResourceProviderImpl
import com.hiaashuu.appteka.user.api.UserBrief
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import com.tomclaw.bananalytics.Bananalytics
import dagger.Module
import dagger.Provides
import java.util.Locale

@Module
class RateModule(
    private val context: Context,
    private val appId: String,
    private val userBrief: UserBrief,
    private val startRating: Float,
    private val startReview: String,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        bananalytics: Bananalytics,
        interactor: RateInteractor,
        locale: Locale,
        resourceProvider: RateResourceProvider,
        schedulers: SchedulersFactory
    ): RatePresenter = RatePresenterImpl(
        appId,
        userBrief,
        startRating,
        startReview,
        bananalytics,
        interactor,
        locale,
        resourceProvider,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideResourceProvider(): RateResourceProvider = RateResourceProviderImpl(
        context.resources
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        schedulers: SchedulersFactory
    ): RateInteractor = RateInteractorImpl(api, schedulers)

}