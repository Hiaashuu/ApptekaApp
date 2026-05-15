package com.hiaashuu.appteka.screen.gallery.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.core.StreamsProvider
import com.hiaashuu.appteka.screen.gallery.GalleryInteractor
import com.hiaashuu.appteka.screen.gallery.GalleryInteractorImpl
import com.hiaashuu.appteka.screen.gallery.GalleryItem
import com.hiaashuu.appteka.screen.gallery.GalleryPresenter
import com.hiaashuu.appteka.screen.gallery.GalleryPresenterImpl
import com.hiaashuu.appteka.screen.gallery.GalleryResourceProvider
import com.hiaashuu.appteka.screen.gallery.GalleryResourceProviderImpl
import com.hiaashuu.appteka.screen.gallery.adapter.image.ImageItemBlueprint
import com.hiaashuu.appteka.screen.gallery.adapter.image.ImageItemPresenter
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.Locale

@Module
class GalleryModule(
    private val context: Context,
    private val items: List<GalleryItem>,
    private val startIndex: Int,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        resourceProvider: GalleryResourceProvider,
        adapterPresenter: Lazy<AdapterPresenter>,
        interactor: GalleryInteractor,
        schedulers: SchedulersFactory
    ): GalleryPresenter = GalleryPresenterImpl(
        items,
        startIndex,
        resourceProvider,
        adapterPresenter,
        interactor,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideGalleryInteractor(
        streamsProvider: StreamsProvider,
        schedulers: SchedulersFactory,
    ): GalleryInteractor = GalleryInteractorImpl(streamsProvider, schedulers)

    @Provides
    @PerActivity
    internal fun provideAdapterPresenter(binder: ItemBinder): AdapterPresenter {
        return SimpleAdapterPresenter(binder)
    }

    @Provides
    @PerActivity
    internal fun provideItemBinder(
        blueprintSet: Set<@JvmSuppressWildcards ItemBlueprint<*, *>>
    ): ItemBinder {
        return ItemBinder.Builder().apply {
            blueprintSet.forEach { registerItem(it) }
        }.build()
    }

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideImageItemBlueprint(
        presenter: ImageItemPresenter
    ): ItemBlueprint<*, *> = ImageItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideImageItemPresenter() = ImageItemPresenter()

    @Provides
    @PerActivity
    internal fun provideGalleryResourceProvider(locale: Locale): GalleryResourceProvider =
        GalleryResourceProviderImpl(context.resources, locale)

}