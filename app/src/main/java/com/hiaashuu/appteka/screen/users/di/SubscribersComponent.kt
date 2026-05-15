package com.hiaashuu.appteka.screen.users.di

import com.hiaashuu.appteka.screen.users.UsersFragment
import com.hiaashuu.appteka.util.PerFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [SubscribersModule::class])
interface SubscribersComponent {

    fun inject(fragment: UsersFragment)

}