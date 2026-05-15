package com.hiaashuu.appteka.screen.avatar_crop.di

import android.content.Context
import android.net.Uri
import android.os.Bundle
import com.hiaashuu.appteka.di.PICKED_MEDIA_CACHE
import com.hiaashuu.appteka.screen.avatar_crop.AvatarCropInteractor
import com.hiaashuu.appteka.screen.avatar_crop.AvatarCropInteractorImpl
import com.hiaashuu.appteka.screen.avatar_crop.AvatarCropPresenter
import com.hiaashuu.appteka.screen.avatar_crop.AvatarCropPresenterImpl
import com.hiaashuu.appteka.screen.avatar_crop.AvatarCropResourceProvider
import com.hiaashuu.appteka.screen.avatar_crop.AvatarCropResourceProviderImpl
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import com.tomclaw.cache.DiskLruCache
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AvatarCropModule(
    private val context: Context,
    private val sourceUri: Uri,
    private val cacheKey: String,
    private val state: Bundle?,
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: AvatarCropInteractor,
        resourceProvider: AvatarCropResourceProvider,
        schedulers: SchedulersFactory,
    ): AvatarCropPresenter = AvatarCropPresenterImpl(
        sourceUri,
        cacheKey,
        interactor,
        resourceProvider,
        schedulers,
        state,
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        @Named(PICKED_MEDIA_CACHE) pickedMediaCache: DiskLruCache,
        schedulers: SchedulersFactory,
    ): AvatarCropInteractor = AvatarCropInteractorImpl(
        context = context,
        contentResolver = context.contentResolver,
        pickedMediaCache = pickedMediaCache,
        schedulers = schedulers,
    )

    @Provides
    @PerActivity
    internal fun provideResourceProvider(): AvatarCropResourceProvider =
        AvatarCropResourceProviderImpl(context.resources)

}