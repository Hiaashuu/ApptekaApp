package com.hiaashuu.appteka.screen.unlink.di

import com.hiaashuu.appteka.screen.unlink.UnlinkActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [UnlinkModule::class])
interface UnlinkComponent {

    fun inject(activity: UnlinkActivity)

}