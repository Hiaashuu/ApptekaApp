package com.hiaashuu.appteka.screen.avatar_crop.di

import com.hiaashuu.appteka.screen.avatar_crop.AvatarCropActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [AvatarCropModule::class])
interface AvatarCropComponent {

    fun inject(activity: AvatarCropActivity)

}