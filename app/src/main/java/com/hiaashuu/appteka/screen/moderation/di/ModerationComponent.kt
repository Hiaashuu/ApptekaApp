package com.hiaashuu.appteka.screen.moderation.di

import com.hiaashuu.appteka.screen.moderation.ModerationActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ModerationModule::class])
interface ModerationComponent {

    fun inject(activity: ModerationActivity)

}