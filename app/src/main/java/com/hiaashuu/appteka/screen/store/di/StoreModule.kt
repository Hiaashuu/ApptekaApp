package com.hiaashuu.appteka.screen.store.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.categories.CategoriesInteractor
import com.hiaashuu.appteka.categories.CategoryConverter
import com.hiaashuu.appteka.categories.CategoryConverterImpl
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiResourceProvider
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiResourceProviderImpl
import com.hiaashuu.appteka.screen.store.AppConverter
import com.hiaashuu.appteka.screen.store.AppConverterImpl
import com.hiaashuu.appteka.screen.store.AppsResourceProvider
import com.hiaashuu.appteka.screen.store.AppsResourceProviderImpl
import com.hiaashuu.appteka.screen.store.CategoryDropdownItemConverter
import com.hiaashuu.appteka.screen.store.CategoryDropdownItemConverterImpl
import com.hiaashuu.appteka.screen.store.StoreInteractor
import com.hiaashuu.appteka.screen.store.StoreInteractorImpl
import com.hiaashuu.appteka.screen.store.StorePreferencesProvider
import com.hiaashuu.appteka.screen.store.StorePreferencesProviderImpl
import com.hiaashuu.appteka.screen.store.StorePresenter
import com.hiaashuu.appteka.screen.store.StorePresenterImpl
import com.hiaashuu.appteka.screen.store.adapter.app.AppItemBlueprint
import com.hiaashuu.appteka.screen.store.adapter.app.AppItemPresenter
import com.hiaashuu.appteka.util.Analytics
import com.hiaashuu.appteka.util.PackageObserver
import com.hiaashuu.appteka.util.PerFragment
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.Locale

@Module
class StoreModule(
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerFragment
    internal fun providePresenter(
        storeInteractor: StoreInteractor,
        categoriesInteractor: CategoriesInteractor,
        categoryConverter: CategoryConverter,
        dropdownItemConverter: CategoryDropdownItemConverter,
        adapterPresenter: Lazy<AdapterPresenter>,
        appConverter: AppConverter,
        analytics: Analytics,
        schedulers: SchedulersFactory
    ): StorePresenter = StorePresenterImpl(
        storeInteractor,
        categoriesInteractor,
        categoryConverter,
        dropdownItemConverter,
        adapterPresenter,
        appConverter,
        analytics,
        schedulers,
        state
    )

    @Provides
    @PerFragment
    internal fun provideInteractor(
        api: StoreApi,
        locale: Locale,
        schedulers: SchedulersFactory
    ): StoreInteractor = StoreInteractorImpl(api, locale, schedulers)

    @Provides
    @PerFragment
    internal fun provideResourceProvider(): AppsResourceProvider {
        return AppsResourceProviderImpl(context.resources)
    }

    @Provides
    @PerFragment
    internal fun provideStorePreferencesProvider(): StorePreferencesProvider {
        return StorePreferencesProviderImpl(context)
    }

    @Provides
    @PerFragment
    internal fun provideAppsConverter(
        resourceProvider: AppsResourceProvider,
        categoryConverter: CategoryConverter,
        packageObserver: PackageObserver,
        abiResourceProvider: AbiResourceProvider,
    ): AppConverter {
        return AppConverterImpl(resourceProvider, categoryConverter, packageObserver, abiResourceProvider)
    }

    @Provides
    @PerFragment
    internal fun provideAbiResourceProvider(): AbiResourceProvider {
        return AbiResourceProviderImpl(context.resources)
    }

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
    internal fun provideAppItemBlueprint(
        presenter: AppItemPresenter
    ): ItemBlueprint<*, *> = AppItemBlueprint(presenter)

    @Provides
    @PerFragment
    internal fun provideAppItemPresenter(
        presenter: StorePresenter,
        resourceProvider: AppsResourceProvider
    ) = AppItemPresenter(presenter, resourceProvider)

    @Provides
    @PerFragment
    internal fun provideCategoryConverter(locale: Locale): CategoryConverter =
        CategoryConverterImpl(locale)

    @Provides
    @PerFragment
    internal fun provideCategoryDropdownItemConverter(): CategoryDropdownItemConverter =
        CategoryDropdownItemConverterImpl(context.resources)

}