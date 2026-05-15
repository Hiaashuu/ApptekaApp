package com.hiaashuu.appteka.screen.uploads.di

import com.hiaashuu.appteka.screen.uploads.UploadsActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [UploadsModule::class])
interface UploadsComponent {

    fun inject(activity: UploadsActivity)

}