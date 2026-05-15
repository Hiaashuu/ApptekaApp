package com.hiaashuu.appteka.screen.change_email.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.change_email.ChangeEmailInteractor
import com.hiaashuu.appteka.screen.change_email.ChangeEmailInteractorImpl
import com.hiaashuu.appteka.screen.change_email.ChangeEmailPresenter
import com.hiaashuu.appteka.screen.change_email.ChangeEmailPresenterImpl
import com.hiaashuu.appteka.screen.change_email.ChangeEmailResourceProvider
import com.hiaashuu.appteka.screen.change_email.ChangeEmailResourceProviderImpl
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Module
import dagger.Provides

@Module
class ChangeEmailModule(
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        resourceProvider: ChangeEmailResourceProvider,
        interactor: ChangeEmailInteractor,
        schedulers: SchedulersFactory
    ): ChangeEmailPresenter = ChangeEmailPresenterImpl(
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
    ): ChangeEmailInteractor = ChangeEmailInteractorImpl(api, schedulers)

    @Provides
    @PerActivity
    internal fun provideResourceProvider(): ChangeEmailResourceProvider =
        ChangeEmailResourceProviderImpl(context.resources)

}