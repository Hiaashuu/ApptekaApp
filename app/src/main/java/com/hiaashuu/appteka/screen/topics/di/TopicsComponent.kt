package com.hiaashuu.appteka.screen.topics.di

import com.hiaashuu.appteka.screen.topics.TopicsFragment
import com.hiaashuu.appteka.util.PerFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [TopicsModule::class])
interface TopicsComponent {

    fun inject(fragment: TopicsFragment)

}