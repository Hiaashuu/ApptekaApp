package com.hiaashuu.appteka.screen.downloads.di

import com.hiaashuu.appteka.screen.downloads.DownloadsActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [DownloadsModule::class])
interface DownloadsComponent {

    fun inject(activity: DownloadsActivity)

}