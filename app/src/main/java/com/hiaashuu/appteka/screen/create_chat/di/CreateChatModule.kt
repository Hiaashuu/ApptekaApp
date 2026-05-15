package com.hiaashuu.appteka.screen.create_chat.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.create_chat.CreateChatInteractor
import com.hiaashuu.appteka.screen.create_chat.CreateChatInteractorImpl
import com.hiaashuu.appteka.screen.create_chat.CreateChatPresenter
import com.hiaashuu.appteka.screen.create_chat.CreateChatPresenterImpl
import com.hiaashuu.appteka.screen.create_chat.CreateChatResourceProvider
import com.hiaashuu.appteka.screen.create_chat.CreateChatResourceProviderImpl
import com.hiaashuu.appteka.util.ImageCompressor
import com.hiaashuu.appteka.util.ImageCompressorImpl
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Module
import dagger.Provides

@Module
class CreateChatModule(
    private val context: Context,
    private val state: Bundle?,
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: CreateChatInteractor,
        resourceProvider: CreateChatResourceProvider,
        schedulers: SchedulersFactory,
    ): CreateChatPresenter = CreateChatPresenterImpl(
        interactor,
        resourceProvider,
        schedulers,
        state,
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        compressor: ImageCompressor,
        schedulers: SchedulersFactory,
    ): CreateChatInteractor = CreateChatInteractorImpl(api, compressor, schedulers)

    @Provides
    @PerActivity
    internal fun provideImageCompressor(): ImageCompressor =
        ImageCompressorImpl(context.contentResolver)

    @Provides
    @PerActivity
    internal fun provideResourceProvider(): CreateChatResourceProvider =
        CreateChatResourceProviderImpl(context.resources)

}