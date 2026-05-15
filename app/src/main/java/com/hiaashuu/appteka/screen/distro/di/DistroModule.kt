package com.hiaashuu.appteka.screen.distro.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.core.PackageInfoProvider
import com.hiaashuu.appteka.core.StreamsProvider
import com.hiaashuu.appteka.download.ApkStorage
import com.hiaashuu.appteka.screen.distro.ApkConverter
import com.hiaashuu.appteka.screen.distro.ApkConverterImpl
import com.hiaashuu.appteka.screen.distro.DistroInfoProvider
import com.hiaashuu.appteka.screen.distro.DistroInfoProviderImpl
import com.hiaashuu.appteka.screen.distro.DistroInteractor
import com.hiaashuu.appteka.screen.distro.DistroInteractorImpl
import com.hiaashuu.appteka.screen.distro.DistroPreferencesProvider
import com.hiaashuu.appteka.screen.distro.DistroPreferencesProviderImpl
import com.hiaashuu.appteka.screen.distro.DistroPresenter
import com.hiaashuu.appteka.screen.distro.DistroPresenterImpl
import com.hiaashuu.appteka.screen.distro.DistroResourceProvider
import com.hiaashuu.appteka.screen.distro.DistroResourceProviderImpl
import com.hiaashuu.appteka.screen.distro.adapter.apk.ApkItemBlueprint
import com.hiaashuu.appteka.screen.distro.adapter.apk.ApkItemPresenter
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.Locale

@Module
class DistroModule(
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        preferencesProvider: DistroPreferencesProvider,
        interactor: DistroInteractor,
        adapterPresenter: Lazy<AdapterPresenter>,
        appConverter: ApkConverter,
        schedulers: SchedulersFactory
    ): DistroPresenter = DistroPresenterImpl(
        preferencesProvider,
        interactor,
        adapterPresenter,
        appConverter,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        infoProvider: DistroInfoProvider,
        apkStorage: ApkStorage,
        streamsProvider: StreamsProvider,
        schedulers: SchedulersFactory
    ): DistroInteractor = DistroInteractorImpl(
        infoProvider,
        apkStorage,
        streamsProvider,
        schedulers
    )

    @Provides
    @PerActivity
    internal fun provideResourceProvider(locale: Locale): DistroResourceProvider {
        return DistroResourceProviderImpl(context.resources, locale)
    }

    @Provides
    @PerActivity
    internal fun provideDistroInfoProvider(
        apkStorage: ApkStorage,
        packageInfoProvider: PackageInfoProvider,
    ): DistroInfoProvider {
        return DistroInfoProviderImpl(
            apkStorage = apkStorage,
            packageInfoProvider = packageInfoProvider
        )
    }

    @Provides
    @PerActivity
    internal fun provideDistroPreferencesProvider(): DistroPreferencesProvider {
        return DistroPreferencesProviderImpl(context)
    }

    @Provides
    @PerActivity
    internal fun provideApkConverter(): ApkConverter = ApkConverterImpl()

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
    internal fun provideApkItemBlueprint(
        presenter: ApkItemPresenter
    ): ItemBlueprint<*, *> = ApkItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideApkItemPresenter(
        presenter: DistroPresenter,
        resourceProvider: DistroResourceProvider
    ) = ApkItemPresenter(presenter, resourceProvider)

}