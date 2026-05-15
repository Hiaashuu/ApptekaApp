package com.hiaashuu.appteka.screen.installed.di

import com.hiaashuu.appteka.screen.installed.InstalledActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [InstalledModule::class])
interface InstalledComponent {

    fun inject(activity: InstalledActivity)

}