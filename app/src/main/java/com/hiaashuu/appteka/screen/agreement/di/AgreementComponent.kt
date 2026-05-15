package com.hiaashuu.appteka.screen.agreement.di

import com.hiaashuu.appteka.screen.agreement.AgreementActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [AgreementModule::class])
interface AgreementComponent {

    fun inject(activity: AgreementActivity)

}