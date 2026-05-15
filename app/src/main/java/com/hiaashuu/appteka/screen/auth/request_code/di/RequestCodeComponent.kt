package com.hiaashuu.appteka.screen.auth.request_code.di

import com.hiaashuu.appteka.screen.auth.request_code.RequestCodeActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [RequestCodeModule::class])
interface RequestCodeComponent {

    fun inject(activity: RequestCodeActivity)

}