package com.hiaashuu.appteka.download.di

import com.hiaashuu.appteka.download.DownloadService
import com.hiaashuu.appteka.util.PerService
import dagger.Subcomponent

@PerService
@Subcomponent(modules = [DownloadServiceModule::class])
interface DownloadServiceComponent {

    fun inject(service: DownloadService)

}