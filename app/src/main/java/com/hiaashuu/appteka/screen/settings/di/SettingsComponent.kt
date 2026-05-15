package com.hiaashuu.appteka.screen.settings.di

import com.hiaashuu.appteka.screen.settings.SettingsActivity
import com.hiaashuu.appteka.screen.settings.SettingsFragment
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.PerFragment
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [SettingsActivityModule::class])
interface SettingsActivityComponent {

    fun inject(activity: SettingsActivity)

}

@PerFragment
@Subcomponent(modules = [SettingsModule::class])
interface SettingsComponent {

    fun inject(fragment: SettingsFragment)

}