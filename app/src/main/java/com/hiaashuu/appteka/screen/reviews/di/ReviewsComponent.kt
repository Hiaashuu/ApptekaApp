package com.hiaashuu.appteka.screen.reviews.di

import com.hiaashuu.appteka.screen.reviews.ReviewsActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ReviewsModule::class])
interface ReviewsComponent {

    fun inject(activity: ReviewsActivity)

}