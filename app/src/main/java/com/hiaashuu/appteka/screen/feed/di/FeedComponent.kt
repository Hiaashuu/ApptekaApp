package com.hiaashuu.appteka.screen.feed.di

import com.hiaashuu.appteka.screen.feed.FeedFragment
import com.hiaashuu.appteka.util.PerFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [FeedModule::class])
interface FeedComponent {

    fun inject(fragment: FeedFragment)

}