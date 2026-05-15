package com.hiaashuu.appteka.screen.permissions.di

import com.hiaashuu.appteka.screen.permissions.PermissionsActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [PermissionsModule::class])
interface PermissionsComponent {

    fun inject(activity: PermissionsActivity)

}