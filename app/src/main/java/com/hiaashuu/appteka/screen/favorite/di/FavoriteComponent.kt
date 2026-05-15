package com.hiaashuu.appteka.screen.favorite.di

import com.hiaashuu.appteka.screen.favorite.FavoriteActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [FavoriteModule::class])
interface FavoriteComponent {

    fun inject(activity: FavoriteActivity)

}