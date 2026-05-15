package com.hiaashuu.appteka.screen.settings.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.download.ApkStorage
import com.hiaashuu.appteka.screen.settings.SettingsInteractor
import com.hiaashuu.appteka.screen.settings.SettingsInteractorImpl
import com.hiaashuu.appteka.screen.settings.SettingsPresenter
import com.hiaashuu.appteka.screen.settings.SettingsPresenterImpl
import com.hiaashuu.appteka.screen.settings.SettingsResourceProvider
import com.hiaashuu.appteka.screen.settings.SettingsResourceProviderImpl
import com.hiaashuu.appteka.util.Analytics
import com.hiaashuu.appteka.util.PerFragment
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Module
import dagger.Provides

@Module
class SettingsModule(
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerFragment
    internal fun providePresenter(
        settingsInteractor: SettingsInteractor,
        apkStorage: ApkStorage,
        resourceProvider: SettingsResourceProvider,
        analytics: Analytics,
        schedulers: SchedulersFactory
    ): SettingsPresenter = SettingsPresenterImpl(
        settingsInteractor,
        apkStorage,
        resourceProvider,
        analytics,
        schedulers,
        state
    )

    @Provides
    @PerFragment
    internal fun provideResourceProvider(): SettingsResourceProvider =
        SettingsResourceProviderImpl(context.resources)

    @Provides
    @PerFragment
    internal fun provideInteractor(
        apkStorage: ApkStorage,
        resourceProvider: SettingsResourceProvider,
        schedulers: SchedulersFactory
    ): SettingsInteractor = SettingsInteractorImpl(context, apkStorage, resourceProvider, schedulers)

}