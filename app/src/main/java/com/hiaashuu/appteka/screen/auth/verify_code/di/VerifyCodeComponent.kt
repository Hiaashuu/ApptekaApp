package com.hiaashuu.appteka.screen.auth.verify_code.di

import com.hiaashuu.appteka.screen.auth.verify_code.VerifyCodeActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [VerifyCodeModule::class])
interface VerifyCodeComponent {

    fun inject(activity: VerifyCodeActivity)

}