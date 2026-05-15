package com.hiaashuu.appteka.screen.uploads.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.categories.CategoryConverter
import com.hiaashuu.appteka.categories.CategoryConverterImpl
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiResourceProvider
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiResourceProviderImpl
import com.hiaashuu.appteka.screen.uploads.AppConverter
import com.hiaashuu.appteka.screen.uploads.AppConverterImpl
import com.hiaashuu.appteka.screen.uploads.AppsResourceProvider
import com.hiaashuu.appteka.screen.uploads.AppsResourceProviderImpl
import com.hiaashuu.appteka.screen.uploads.UploadsInteractor
import com.hiaashuu.appteka.screen.uploads.UploadsInteractorImpl
import com.hiaashuu.appteka.screen.uploads.UploadsPresenter
import com.hiaashuu.appteka.screen.uploads.UploadsPresenterImpl
import com.hiaashuu.appteka.screen.uploads.adapter.app.AppItemBlueprint
import com.hiaashuu.appteka.screen.uploads.adapter.app.AppItemPresenter
import com.hiaashuu.appteka.util.PackageObserver
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.Locale

@Module
class UploadsModule(
    private val context: Context,
    private val userId: Int,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: UploadsInteractor,
        adapterPresenter: Lazy<AdapterPresenter>,
        appConverter: AppConverter,
        schedulers: SchedulersFactory
    ): UploadsPresenter = UploadsPresenterImpl(
        interactor,
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
        schedulers: SchedulersFactory
    ): UploadsInteractor = UploadsInteractorImpl(
        api,
        userId,
        locale,
        schedulers
    )

    @Provides
    @PerActivity
    internal fun provideResourceProvider(): AppsResourceProvider {
        return AppsResourceProviderImpl(context.resources)
    }

    @Provides
    @PerActivity
    internal fun provideAppsConverter(
        resourceProvider: AppsResourceProvider,
        categoryConverter: CategoryConverter,
        packageObserver: PackageObserver,
        abiResourceProvider: AbiResourceProvider
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
        presenter: UploadsPresenter,
        resourceProvider: AppsResourceProvider
    ) = AppItemPresenter(presenter, resourceProvider)

}