package com.hiaashuu.appteka.screen.home.di

import com.hiaashuu.appteka.screen.home.HomeActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {

    fun inject(activity: HomeActivity)

}