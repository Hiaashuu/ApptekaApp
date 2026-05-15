package com.hiaashuu.appteka.screen.store.di

import com.hiaashuu.appteka.screen.store.StoreFragment
import com.hiaashuu.appteka.util.PerFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [StoreModule::class])
interface StoreComponent {

    fun inject(fragment: StoreFragment)

}