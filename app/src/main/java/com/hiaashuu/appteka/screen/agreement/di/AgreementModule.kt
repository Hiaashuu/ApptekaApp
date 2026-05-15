package com.hiaashuu.appteka.screen.agreement.di

import android.os.Bundle
import com.hiaashuu.appteka.screen.agreement.AgreementPresenter
import com.hiaashuu.appteka.screen.agreement.AgreementPresenterImpl
import com.hiaashuu.appteka.util.PerActivity
import dagger.Module
import dagger.Provides

@Module
class AgreementModule(
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(): AgreementPresenter = AgreementPresenterImpl(state)

}