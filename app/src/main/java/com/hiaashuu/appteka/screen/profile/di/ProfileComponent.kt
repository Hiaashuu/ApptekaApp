package com.hiaashuu.appteka.screen.profile.di

import com.hiaashuu.appteka.screen.profile.ProfileFragment
import com.hiaashuu.appteka.util.PerFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {

    fun inject(fragment: ProfileFragment)

}