package com.hiaashuu.appteka.screen.subscriptions.di

import android.os.Bundle
import com.hiaashuu.appteka.screen.subscriptions.SubscriptionsPresenter
import com.hiaashuu.appteka.screen.subscriptions.SubscriptionsPresenterImpl
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Module
import dagger.Provides

@Module
class SubscriptionsModule(
    private val userId: Int,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        schedulers: SchedulersFactory
    ): SubscriptionsPresenter = SubscriptionsPresenterImpl(
        schedulers,
        state
    )

}