package com.hiaashuu.appteka.upload.di

import com.hiaashuu.appteka.upload.UploadService
import com.hiaashuu.appteka.util.PerService
import dagger.Subcomponent

@PerService
@Subcomponent(modules = [UploadServiceModule::class])
interface UploadServiceComponent {

    fun inject(service: UploadService)

}