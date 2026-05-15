package com.hiaashuu.appteka.screen.upload.di

import com.hiaashuu.appteka.screen.upload.UploadActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [UploadModule::class])
interface UploadComponent {

    fun inject(activity: UploadActivity)

}