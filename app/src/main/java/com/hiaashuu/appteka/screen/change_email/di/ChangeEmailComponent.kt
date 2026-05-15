package com.hiaashuu.appteka.screen.change_email.di

import com.hiaashuu.appteka.screen.change_email.ChangeEmailActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ChangeEmailModule::class])
interface ChangeEmailComponent {

    fun inject(activity: ChangeEmailActivity)

}