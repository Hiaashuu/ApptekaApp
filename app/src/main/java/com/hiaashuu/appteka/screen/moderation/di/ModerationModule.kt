package com.hiaashuu.appteka.screen.moderation.di

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
import com.hiaashuu.appteka.screen.moderation.AppConverter
import com.hiaashuu.appteka.screen.moderation.AppConverterImpl
import com.hiaashuu.appteka.screen.moderation.AppsResourceProvider
import com.hiaashuu.appteka.screen.moderation.AppsResourceProviderImpl
import com.hiaashuu.appteka.screen.moderation.ModerationInteractor
import com.hiaashuu.appteka.screen.moderation.ModerationInteractorImpl
import com.hiaashuu.appteka.screen.moderation.ModerationPresenter
import com.hiaashuu.appteka.screen.moderation.ModerationPresenterImpl
import com.hiaashuu.appteka.screen.moderation.ModerationResourceProvider
import com.hiaashuu.appteka.screen.moderation.ModerationResourceProviderImpl
import com.hiaashuu.appteka.user.ModerationProvider
import com.hiaashuu.appteka.screen.moderation.adapter.app.AppItemBlueprint
import com.hiaashuu.appteka.screen.moderation.adapter.app.AppItemPresenter
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.Locale

@Module
class ModerationModule(
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: ModerationInteractor,
        moderationProvider: ModerationProvider,
        capabilitiesProvider: com.hiaashuu.appteka.core.permissions.UserCapabilitiesProvider,
        adapterPresenter: Lazy<AdapterPresenter>,
        appConverter: AppConverter,
        resourceProvider: ModerationResourceProvider,
        schedulers: SchedulersFactory
    ): ModerationPresenter = ModerationPresenterImpl(
        interactor,
        moderationProvider,
        capabilitiesProvider,
        adapterPresenter,
        appConverter,
        resourceProvider,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideModerationResourceProvider(): ModerationResourceProvider =
        ModerationResourceProviderImpl(context.resources)

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        locale: Locale,
        schedulers: SchedulersFactory
    ): ModerationInteractor = ModerationInteractorImpl(api, locale, schedulers)

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
        abiResourceProvider: AbiResourceProvider
    ): AppConverter {
        return AppConverterImpl(resourceProvider, categoryConverter, abiResourceProvider)
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
    internal fun provideAppItemPresenter(presenter: ModerationPresenter) =
        AppItemPresenter(presenter)

}