package com.hiaashuu.appteka.screen.feed.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.core.TimeProvider
import com.hiaashuu.appteka.screen.feed.FeedConverter
import com.hiaashuu.appteka.screen.feed.FeedConverterImpl
import com.hiaashuu.appteka.screen.feed.FeedInteractor
import com.hiaashuu.appteka.screen.feed.FeedInteractorImpl
import com.hiaashuu.appteka.screen.feed.FeedPreferencesProvider
import com.hiaashuu.appteka.screen.feed.FeedPreferencesProviderImpl
import com.hiaashuu.appteka.screen.feed.FeedPresenter
import com.hiaashuu.appteka.screen.feed.FeedPresenterImpl
import com.hiaashuu.appteka.screen.feed.FeedResourceProvider
import com.hiaashuu.appteka.screen.feed.FeedResourceProviderImpl
import com.hiaashuu.appteka.screen.feed.adapter.favorite.FavoriteItemBlueprint
import com.hiaashuu.appteka.screen.feed.adapter.favorite.FavoriteItemPresenter
import com.hiaashuu.appteka.screen.feed.adapter.subscribe.SubscribeItemBlueprint
import com.hiaashuu.appteka.screen.feed.adapter.subscribe.SubscribeItemPresenter
import com.hiaashuu.appteka.screen.feed.adapter.text.TextItemBlueprint
import com.hiaashuu.appteka.screen.feed.adapter.text.TextItemPresenter
import com.hiaashuu.appteka.screen.feed.adapter.unauthorized.UnauthorizedItemBlueprint
import com.hiaashuu.appteka.screen.feed.adapter.unauthorized.UnauthorizedItemPresenter
import com.hiaashuu.appteka.screen.feed.adapter.upload.UploadItemBlueprint
import com.hiaashuu.appteka.screen.feed.adapter.upload.UploadItemPresenter
import com.hiaashuu.appteka.util.PerFragment
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Named
import java.util.Locale

@Module
class FeedModule(
    private val context: Context,
    private val userId: Int?,
    private val postId: Int?,
    private val withToolbar: Boolean?,
    private val state: Bundle?
) {

    @Provides
    @PerFragment
    internal fun providePresenter(
        interactor: FeedInteractor,
        adapterPresenter: Lazy<AdapterPresenter>,
        converter: FeedConverter,
        resourceProvider: FeedResourceProvider,
        schedulers: SchedulersFactory
    ): FeedPresenter = FeedPresenterImpl(
        userId,
        postId,
        withToolbar,
        interactor,
        adapterPresenter,
        converter,
        resourceProvider,
        schedulers,
        state,
    )

    @Provides
    @PerFragment
    internal fun provideInteractor(
        api: StoreApi,
        schedulers: SchedulersFactory
    ): FeedInteractor = FeedInteractorImpl(api, schedulers)

    @Provides
    @PerFragment
    internal fun provideFeedConverter(): FeedConverter {
        return FeedConverterImpl()
    }

    @Provides
    @PerFragment
    internal fun provideResourceProvider(
        locale: Locale,
        timeProvider: TimeProvider
    ): FeedResourceProvider =
        FeedResourceProviderImpl(context.resources, locale, timeProvider)

    @Provides
    @PerFragment
    internal fun providePreferencesProvider(): FeedPreferencesProvider =
        FeedPreferencesProviderImpl(context)

    @Provides
    @PerFragment
    internal fun provideAdapterPresenter(binder: ItemBinder): AdapterPresenter {
        return SimpleAdapterPresenter(binder)
    }

    @Provides
    @PerFragment
    internal fun provideItemBinder(
        blueprintSet: Set<@JvmSuppressWildcards ItemBlueprint<*, *>>
    ): ItemBinder {
        return ItemBinder.Builder().apply {
            blueprintSet.forEach { registerItem(it) }
        }.build()
    }

    @Provides
    @IntoSet
    @PerFragment
    internal fun provideTextItemBlueprint(
        itemPresenter: TextItemPresenter,
        feedPresenter: FeedPresenter,
    ): ItemBlueprint<*, *> = TextItemBlueprint(itemPresenter, feedPresenter)

    @Provides
    @PerFragment
    internal fun provideTextItemPresenter(
        locale: Locale,
        resourceProvider: FeedResourceProvider,
        presenter: FeedPresenter,
    ) = TextItemPresenter(locale, resourceProvider, presenter)

    @Provides
    @IntoSet
    @PerFragment
    internal fun provideFavoriteItemBlueprint(
        itemPresenter: FavoriteItemPresenter,
        feedPresenter: FeedPresenter,
    ): ItemBlueprint<*, *> = FavoriteItemBlueprint(itemPresenter, feedPresenter)

    @Provides
    @PerFragment
    internal fun provideFavoriteItemPresenter(
        locale: Locale,
        resourceProvider: FeedResourceProvider,
        presenter: FeedPresenter,
    ) = FavoriteItemPresenter(locale, resourceProvider, presenter)

    @Provides
    @IntoSet
    @PerFragment
    internal fun provideUploadItemBlueprint(
        itemPresenter: UploadItemPresenter,
        feedPresenter: FeedPresenter,
    ): ItemBlueprint<*, *> = UploadItemBlueprint(itemPresenter, feedPresenter)

    @Provides
    @PerFragment
    internal fun provideUploadItemPresenter(
        locale: Locale,
        resourceProvider: FeedResourceProvider,
        presenter: FeedPresenter,
    ) = UploadItemPresenter(locale, resourceProvider, presenter)

    @Provides
    @IntoSet
    @PerFragment
    internal fun provideSubscribeItemBlueprint(
        itemPresenter: SubscribeItemPresenter,
    ): ItemBlueprint<*, *> = SubscribeItemBlueprint(itemPresenter)

    @Provides
    @PerFragment
    internal fun provideSubscribeItemPresenter(
        locale: Locale,
        resourceProvider: FeedResourceProvider,
        presenter: FeedPresenter,
    ) = SubscribeItemPresenter(locale, resourceProvider, presenter)

    @Provides
    @IntoSet
    @PerFragment
    internal fun provideUnauthorizedItemBlueprint(
        itemPresenter: UnauthorizedItemPresenter,
    ): ItemBlueprint<*, *> = UnauthorizedItemBlueprint(itemPresenter)

    @Provides
    @PerFragment
    internal fun provideUnauthorizedItemPresenter(
        presenter: FeedPresenter,
    ) = UnauthorizedItemPresenter(presenter)

}