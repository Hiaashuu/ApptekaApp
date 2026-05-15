package com.hiaashuu.appteka.screen.auth.verify_code.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.auth.verify_code.VerifyCodeInteractor
import com.hiaashuu.appteka.screen.auth.verify_code.VerifyCodeInteractorImpl
import com.hiaashuu.appteka.screen.auth.verify_code.VerifyCodePresenter
import com.hiaashuu.appteka.screen.auth.verify_code.VerifyCodePresenterImpl
import com.hiaashuu.appteka.screen.auth.verify_code.VerifyCodeResourceProvider
import com.hiaashuu.appteka.screen.auth.verify_code.VerifyCodeResourceProviderImpl
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Module
import dagger.Provides

@Module
class VerifyCodeModule(
    private val context: Context,
    private val email: String,
    private val requestId: String,
    private val registered: Boolean,
    private val codeRegex: String,
    private val nameRegex: String,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        resourceProvider: VerifyCodeResourceProvider,
        interactor: VerifyCodeInteractor,
        schedulers: SchedulersFactory
    ): VerifyCodePresenter = VerifyCodePresenterImpl(
        email,
        requestId,
        registered,
        codeRegex,
        nameRegex,
        resourceProvider,
        interactor,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        schedulers: SchedulersFactory
    ): VerifyCodeInteractor = VerifyCodeInteractorImpl(api, schedulers)

    @Provides
    @PerActivity
    internal fun provideVerifyCodeResourceProvider(): VerifyCodeResourceProvider =
        VerifyCodeResourceProviderImpl(resources = context.resources)

}