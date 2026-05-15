package com.hiaashuu.appteka.screen.about.di

import com.hiaashuu.appteka.screen.about.AboutActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [AboutModule::class])
interface AboutComponent {

    fun inject(activity: AboutActivity)

}