package com.hiaashuu.appteka.screen.details.di

import com.hiaashuu.appteka.screen.details.DetailsActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {

    fun inject(activity: DetailsActivity)

}