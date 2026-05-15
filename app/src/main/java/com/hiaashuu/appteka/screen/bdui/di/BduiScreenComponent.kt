package com.hiaashuu.appteka.screen.bdui.di

import com.hiaashuu.appteka.screen.bdui.BduiScreenActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [BduiScreenModule::class])
interface BduiScreenComponent {

    fun inject(activity: BduiScreenActivity)

}