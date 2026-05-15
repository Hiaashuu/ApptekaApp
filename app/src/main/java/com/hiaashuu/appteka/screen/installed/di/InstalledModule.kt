package com.hiaashuu.appteka.screen.installed.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.categories.CategoryConverter
import com.hiaashuu.appteka.categories.CategoryConverterImpl
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.core.StreamsProvider
import com.hiaashuu.appteka.download.ApkStorage
import com.hiaashuu.appteka.screen.installed.AppConverter
import com.hiaashuu.appteka.screen.installed.AppConverterImpl
import com.hiaashuu.appteka.screen.installed.AppsResourceProvider
import com.hiaashuu.appteka.screen.installed.AppsResourceProviderImpl
import com.hiaashuu.appteka.screen.installed.InstalledInfoProvider
import com.hiaashuu.appteka.screen.installed.InstalledInfoProviderImpl
import com.hiaashuu.appteka.screen.installed.InstalledInteractor
import com.hiaashuu.appteka.screen.installed.InstalledInteractorImpl
import com.hiaashuu.appteka.screen.installed.InstalledPreferencesProvider
import com.hiaashuu.appteka.screen.installed.InstalledPreferencesProviderImpl
import com.hiaashuu.appteka.screen.installed.InstalledPresenter
import com.hiaashuu.appteka.screen.installed.InstalledPresenterImpl
import com.hiaashuu.appteka.screen.installed.adapter.app.AppItemBlueprint
import com.hiaashuu.appteka.screen.installed.adapter.app.AppItemPresenter
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.Locale

@Module
class InstalledModule(
    private val context: Context,
    private val picker: Boolean,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        preferencesProvider: InstalledPreferencesProvider,
        interactor: InstalledInteractor,
        apkStorage: ApkStorage,
        adapterPresenter: Lazy<AdapterPresenter>,
        appConverter: AppConverter,
        schedulers: SchedulersFactory
    ): InstalledPresenter = InstalledPresenterImpl(
        picker,
        preferencesProvider,
        interactor,
        apkStorage,
        adapterPresenter,
        appConverter,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        locale: Locale,
        apkStorage: ApkStorage,
        streamsProvider: StreamsProvider,
        infoProvider: InstalledInfoProvider,
        schedulers: SchedulersFactory
    ): InstalledInteractor = InstalledInteractorImpl(
        api,
        locale,
        apkStorage,
        streamsProvider,
        infoProvider,
        schedulers
    )

    @Provides
    @PerActivity
    internal fun provideResourceProvider(locale: Locale): AppsResourceProvider {
        return AppsResourceProviderImpl(context.resources, locale)
    }

    @Provides
    @PerActivity
    internal fun provideInstalledInfoProvider(): InstalledInfoProvider {
        return InstalledInfoProviderImpl(context.packageManager)
    }

    @Provides
    @PerActivity
    internal fun provideInstalledPreferencesProvider(): InstalledPreferencesProvider {
        return InstalledPreferencesProviderImpl(context)
    }

    @Provides
    @PerActivity
    internal fun provideAppsConverter(): AppConverter = AppConverterImpl()

    @Provides
    @PerActivity
    internal fun provideCategoryConverter(locale: Locale): CategoryConverter =
        CategoryConverterImpl(locale)

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
        presenter: InstalledPresenter,
        resourceProvider: AppsResourceProvider
    ) = AppItemPresenter(presenter, resourceProvider)

}