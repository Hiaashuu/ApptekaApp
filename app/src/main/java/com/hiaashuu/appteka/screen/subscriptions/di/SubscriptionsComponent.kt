package com.hiaashuu.appteka.screen.subscriptions.di

import com.hiaashuu.appteka.screen.subscriptions.SubscriptionsActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [SubscriptionsModule::class])
interface SubscriptionsComponent {

    fun inject(activity: SubscriptionsActivity)

}