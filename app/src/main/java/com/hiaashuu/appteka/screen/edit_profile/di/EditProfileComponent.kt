package com.hiaashuu.appteka.screen.edit_profile.di

import com.hiaashuu.appteka.screen.edit_profile.EditProfileActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [EditProfileModule::class])
interface EditProfileComponent {

    fun inject(activity: EditProfileActivity)

}