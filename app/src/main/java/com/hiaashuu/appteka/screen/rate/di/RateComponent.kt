package com.hiaashuu.appteka.screen.rate.di

import com.hiaashuu.appteka.screen.rate.RateActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [RateModule::class])
interface RateComponent {

    fun inject(activity: RateActivity)

}