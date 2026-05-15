package com.hiaashuu.appteka.screen.distro.di

import com.hiaashuu.appteka.screen.distro.DistroActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [DistroModule::class])
interface DistroComponent {

    fun inject(activity: DistroActivity)

}