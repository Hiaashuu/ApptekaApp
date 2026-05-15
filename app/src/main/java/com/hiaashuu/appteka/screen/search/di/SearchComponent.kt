package com.hiaashuu.appteka.screen.search.di

import com.hiaashuu.appteka.screen.search.SearchActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [SearchModule::class])
interface SearchComponent {

    fun inject(activity: SearchActivity)

}