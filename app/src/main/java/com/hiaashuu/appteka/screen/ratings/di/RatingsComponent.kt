package com.hiaashuu.appteka.screen.ratings.di

import com.hiaashuu.appteka.screen.ratings.RatingsActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [RatingsModule::class])
interface RatingsComponent {

    fun inject(activity: RatingsActivity)

}