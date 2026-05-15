package com.hiaashuu.appteka.screen.unpublish.di

import com.hiaashuu.appteka.screen.unpublish.UnpublishActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [UnpublishModule::class])
interface UnpublishComponent {

    fun inject(activity: UnpublishActivity)

}