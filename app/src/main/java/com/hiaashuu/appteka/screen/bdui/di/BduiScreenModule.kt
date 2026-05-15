package com.hiaashuu.appteka.screen.bdui.di

import android.content.Context
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.hiaashuu.appteka.core.HttpClientHolder
import com.hiaashuu.appteka.screen.bdui.BduiScreenInteractor
import com.hiaashuu.appteka.screen.bdui.BduiScreenInteractorImpl
import com.hiaashuu.appteka.screen.bdui.BduiScreenPresenter
import com.hiaashuu.appteka.screen.bdui.BduiScreenPresenterImpl
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import com.hiaashuu.appteka.util.bdui.BduiPreferencesStorage
import com.hiaashuu.appteka.util.bdui.BduiPreferencesStorageImpl
import dagger.Module
import dagger.Provides

@Module
class BduiScreenModule(
    private val url: String,
    private val title: String?,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: BduiScreenInteractor,
        schedulers: SchedulersFactory
    ): BduiScreenPresenter = BduiScreenPresenterImpl(
        url = url,
        title = title,
        interactor = interactor,
        schedulers = schedulers,
        state = state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        httpClientHolder: HttpClientHolder,
        schedulers: SchedulersFactory
    ): BduiScreenInteractor = BduiScreenInteractorImpl(
        httpClientHolder = httpClientHolder,
        schedulers = schedulers
    )

    @Provides
    @PerActivity
    internal fun providePreferencesStorage(
        context: Context,
        gson: Gson
    ): BduiPreferencesStorage = BduiPreferencesStorageImpl(
        preferences = PreferenceManager.getDefaultSharedPreferences(context),
        gson = gson
    )

}