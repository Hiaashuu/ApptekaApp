package com.hiaashuu.appteka.screen.search.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.categories.CategoryConverter
import com.hiaashuu.appteka.categories.CategoryConverterImpl
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.search.SearchInteractor
import com.hiaashuu.appteka.screen.search.SearchInteractorImpl
import com.hiaashuu.appteka.screen.search.SearchPresenter
import com.hiaashuu.appteka.screen.search.SearchPresenterImpl
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiResourceProvider
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiResourceProviderImpl
import com.hiaashuu.appteka.screen.store.AppConverter
import com.hiaashuu.appteka.screen.store.AppConverterImpl
import com.hiaashuu.appteka.screen.store.AppsResourceProvider
import com.hiaashuu.appteka.screen.store.AppsResourceProviderImpl
import com.hiaashuu.appteka.screen.store.StorePreferencesProvider
import com.hiaashuu.appteka.screen.store.StorePreferencesProviderImpl
import com.hiaashuu.appteka.screen.store.adapter.app.AppItemBlueprint
import com.hiaashuu.appteka.screen.store.adapter.app.AppItemPresenter
import com.hiaashuu.appteka.util.Analytics
import com.hiaashuu.appteka.util.PackageObserver
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.Locale

@Module
class SearchModule(
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        searchInteractor: SearchInteractor,
        adapterPresenter: Lazy<AdapterPresenter>,
        appConverter: AppConverter,
        analytics: Analytics,
        schedulers: SchedulersFactory
    ): SearchPresenter = SearchPresenterImpl(
        searchInteractor,
        adapterPresenter,
        appConverter,
        analytics,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        locale: Locale,
        schedulers: SchedulersFactory
    ): SearchInteractor = SearchInteractorImpl(api, locale, schedulers)

    @Provides
    @PerActivity
    internal fun provideResourceProvider(): AppsResourceProvider {
        return AppsResourceProviderImpl(context.resources)
    }

    @Provides
    @PerActivity
    internal fun provideStorePreferencesProvider(): StorePreferencesProvider {
        return StorePreferencesProviderImpl(context)
    }

    @Provides
    @PerActivity
    internal fun provideAppsConverter(
        resourceProvider: AppsResourceProvider,
        categoryConverter: CategoryConverter,
        packageObserver: PackageObserver,
        abiResourceProvider: AbiResourceProvider,
    ): AppConverter {
        return AppConverterImpl(resourceProvider, categoryConverter, packageObserver, abiResourceProvider)
    }

    @Provides
    @PerActivity
    internal fun provideAbiResourceProvider(): AbiResourceProvider {
        return AbiResourceProviderImpl(context.resources)
    }

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
    internal fun provideAppItemBlueprint(
        presenter: AppItemPresenter
    ): ItemBlueprint<*, *> = AppItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideAppItemPresenter(
        presenter: SearchPresenter,
        resourceProvider: AppsResourceProvider
    ) = AppItemPresenter(presenter, resourceProvider)

    @Provides
    @PerActivity
    internal fun provideCategoryConverter(locale: Locale): CategoryConverter =
        CategoryConverterImpl(locale)

}