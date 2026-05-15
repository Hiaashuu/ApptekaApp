package com.hiaashuu.appteka.screen.edit_profile.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.core.permissions.UserCapabilitiesProvider
import com.hiaashuu.appteka.screen.edit_profile.EditProfileInteractor
import com.hiaashuu.appteka.screen.edit_profile.EditProfileInteractorImpl
import com.hiaashuu.appteka.screen.edit_profile.EditProfilePresenter
import com.hiaashuu.appteka.screen.edit_profile.EditProfilePresenterImpl
import com.hiaashuu.appteka.screen.edit_profile.EditProfileResourceProvider
import com.hiaashuu.appteka.screen.edit_profile.EditProfileResourceProviderImpl
import com.hiaashuu.appteka.util.ImageCompressor
import com.hiaashuu.appteka.util.ImageCompressorImpl
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Module
import dagger.Provides

@Module
class EditProfileModule(
    private val context: Context,
    private val state: Bundle?,
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: EditProfileInteractor,
        resourceProvider: EditProfileResourceProvider,
        capabilitiesProvider: UserCapabilitiesProvider,
        schedulers: SchedulersFactory,
    ): EditProfilePresenter = EditProfilePresenterImpl(
        interactor,
        resourceProvider,
        capabilitiesProvider,
        schedulers,
        state,
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        compressor: ImageCompressor,
        schedulers: SchedulersFactory,
    ): EditProfileInteractor = EditProfileInteractorImpl(api, compressor, schedulers)

    @Provides
    @PerActivity
    internal fun provideImageCompressor(): ImageCompressor =
        ImageCompressorImpl(context.contentResolver)

    @Provides
    @PerActivity
    internal fun provideResourceProvider(): EditProfileResourceProvider =
        EditProfileResourceProviderImpl(context.resources)

}