package com.hiaashuu.appteka.screen.about.di

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import com.hiaashuu.appteka.core.AppInfoProvider
import com.hiaashuu.appteka.core.UserAgentProvider
import com.hiaashuu.appteka.screen.about.AboutPresenter
import com.hiaashuu.appteka.screen.about.AboutPresenterImpl
import com.hiaashuu.appteka.screen.about.AboutResourceProvider
import com.hiaashuu.appteka.screen.about.AboutResourceProviderImpl
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Module
import dagger.Provides
import java.util.Locale

@Module
class AboutModule(
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        resourceProvider: AboutResourceProvider,
        userAgentProvider: UserAgentProvider,
        schedulers: SchedulersFactory
    ): AboutPresenter = AboutPresenterImpl(
        resourceProvider,
        userAgentProvider,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideResourceProvider(
        infoProvider: AppInfoProvider,
    ): AboutResourceProvider = AboutResourceProviderImpl(
        infoProvider,
        context.resources,
    )

}