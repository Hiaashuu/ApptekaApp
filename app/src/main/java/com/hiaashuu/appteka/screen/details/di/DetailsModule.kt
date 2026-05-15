package com.hiaashuu.appteka.screen.details.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.di.DATE_FORMATTER
import com.hiaashuu.appteka.download.DownloadManager
import com.hiaashuu.appteka.screen.details.DetailsConverter
import com.hiaashuu.appteka.screen.details.DetailsConverterImpl
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiResourceProvider
import com.hiaashuu.appteka.screen.details.adapter.abi.AbiResourceProviderImpl
import com.hiaashuu.appteka.screen.details.DetailsInteractor
import com.hiaashuu.appteka.screen.details.DetailsInteractorImpl
import com.hiaashuu.appteka.screen.details.DetailsPreferencesProvider
import com.hiaashuu.appteka.screen.details.DetailsPreferencesProviderImpl
import com.hiaashuu.appteka.screen.details.DetailsPresenter
import com.hiaashuu.appteka.screen.details.DetailsPresenterImpl
import com.hiaashuu.appteka.screen.details.DetailsResourceProvider
import com.hiaashuu.appteka.screen.details.DetailsResourceProviderImpl
import com.hiaashuu.appteka.screen.details.adapter.controls.ControlsItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.controls.ControlsItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.description.DescriptionItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.description.DescriptionItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.description.DescriptionResourceProvider
import com.hiaashuu.appteka.screen.details.adapter.description.DescriptionResourceProviderImpl
import com.hiaashuu.appteka.screen.details.adapter.discuss.DiscussItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.discuss.DiscussItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.header.HeaderItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.header.HeaderItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.permissions.PermissionsItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.permissions.PermissionsItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.permissions.PermissionsResourceProvider
import com.hiaashuu.appteka.screen.details.adapter.permissions.PermissionsResourceProviderImpl
import com.hiaashuu.appteka.screen.details.adapter.play.PlayItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.play.PlayItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.play.PlayResourceProvider
import com.hiaashuu.appteka.screen.details.adapter.play.PlayResourceProviderImpl
import com.hiaashuu.appteka.screen.details.adapter.rating.RatingItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.rating.RatingItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.scores.ScoresItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.scores.ScoresItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.screenshot.ScreenshotItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.screenshot.ScreenshotItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.screenshots.ScreenshotsItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.screenshots.ScreenshotsItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.security.SecurityItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.security.SecurityItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.status.StatusItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.status.StatusItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.user_rate.UserRateItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.user_rate.UserRateItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.user_review.UserReviewItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.user_review.UserReviewItemPresenter
import com.hiaashuu.appteka.screen.details.adapter.whats_new.WhatsNewItemBlueprint
import com.hiaashuu.appteka.screen.details.adapter.whats_new.WhatsNewItemPresenter
import com.hiaashuu.appteka.util.PackageObserver
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import com.tomclaw.bananalytics.Bananalytics
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.text.DateFormat
import java.util.Locale
import javax.inject.Named

@Module
class DetailsModule(
    private val appId: String?,
    private val packageName: String?,
    private val moderation: Boolean,
    private val finishOnly: Boolean,
    private val openReview: Boolean,
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        bananalytics: Bananalytics,
        interactor: DetailsInteractor,
        resourceProvider: DetailsResourceProvider,
        abiResourceProvider: AbiResourceProvider,
        @Named(DETAILS_ADAPTER_PRESENTER) adapterPresenter: Lazy<AdapterPresenter>,
        detailsConverter: DetailsConverter,
        packageObserver: PackageObserver,
        downloadManager: DownloadManager,
        schedulers: SchedulersFactory
    ): DetailsPresenter = DetailsPresenterImpl(
        appId,
        packageName,
        moderation,
        finishOnly,
        openReview,
        bananalytics,
        interactor,
        resourceProvider,
        abiResourceProvider,
        adapterPresenter,
        detailsConverter,
        packageObserver,
        downloadManager,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        bananalytics: Bananalytics,
        api: StoreApi,
        locale: Locale,
        schedulers: SchedulersFactory
    ): DetailsInteractor = DetailsInteractorImpl(bananalytics, api, locale, schedulers)

    @Provides
    @PerActivity
    internal fun provideDetailsResourceProvider(
        locale: Locale
    ): DetailsResourceProvider = DetailsResourceProviderImpl(resources = context.resources, locale)

    @Provides
    @PerActivity
    internal fun provideDetailsConverterProvider(
        resourceProvider: DetailsResourceProvider,
        abiResourceProvider: AbiResourceProvider,
        locale: Locale,
    ): DetailsConverter = DetailsConverterImpl(resourceProvider, abiResourceProvider, locale)

    @Provides
    @PerActivity
    internal fun provideAbiResourceProvider(): AbiResourceProvider {
        return AbiResourceProviderImpl(context.resources)
    }

    @Provides
    @Named(DETAILS_ADAPTER_PRESENTER)
    @PerActivity
    internal fun provideDetailsAdapterPresenter(binder: ItemBinder): AdapterPresenter {
        return SimpleAdapterPresenter(binder)
    }

    @Provides
    @Named(SCREENSHOT_ADAPTER_PRESENTER)
    @PerActivity
    internal fun provideScreenshotAdapterPresenter(binder: ItemBinder): AdapterPresenter {
        return SimpleAdapterPresenter(binder)
    }

    @Provides
    @PerActivity
    internal fun provideDetailsPreferencesProvider(): DetailsPreferencesProvider {
        return DetailsPreferencesProviderImpl(context)
    }

    @Provides
    @PerActivity
    internal fun providePlayResourceProvider(): PlayResourceProvider {
        return PlayResourceProviderImpl(context.resources)
    }

    @Provides
    @PerActivity
    internal fun provideDescriptionResourceProvider(locale: Locale): DescriptionResourceProvider {
        return DescriptionResourceProviderImpl(context.resources, locale)
    }

    @Provides
    @PerActivity
    internal fun providePermissionsResourceProvider(): PermissionsResourceProvider {
        return PermissionsResourceProviderImpl(context.resources)
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
    internal fun provideHeaderItemBlueprint(
        presenter: HeaderItemPresenter
    ): ItemBlueprint<*, *> = HeaderItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideHeaderItemPresenter(
        locale: Locale,
        presenter: DetailsPresenter
    ) = HeaderItemPresenter(locale, presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun providePlayItemBlueprint(
        presenter: PlayItemPresenter
    ): ItemBlueprint<*, *> = PlayItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun providePlayItemPresenter(
        locale: Locale,
        resourceProvider: PlayResourceProvider,
        presenter: DetailsPresenter,
    ) = PlayItemPresenter(locale, resourceProvider, presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideWhatsNewItemBlueprint(
        presenter: WhatsNewItemPresenter
    ): ItemBlueprint<*, *> = WhatsNewItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideWhatsNewItemPresenter(
        presenter: DetailsPresenter,
    ) = WhatsNewItemPresenter(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideDescriptionItemBlueprint(
        presenter: DescriptionItemPresenter
    ): ItemBlueprint<*, *> = DescriptionItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideDescriptionItemPresenter(
        presenter: DetailsPresenter,
        resourceProvider: DescriptionResourceProvider
    ) = DescriptionItemPresenter(presenter, resourceProvider)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideAbiItemBlueprint(
        presenter: AbiItemPresenter
    ): ItemBlueprint<*, *> = AbiItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideAbiItemPresenter(
        resourceProvider: AbiResourceProvider
    ) = AbiItemPresenter(resourceProvider)

    @Provides
    @IntoSet
    @PerActivity
    internal fun providePermissionsItemBlueprint(
        presenter: PermissionsItemPresenter
    ): ItemBlueprint<*, *> = PermissionsItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun providePermissionsItemPresenter(
        resourceProvider: PermissionsResourceProvider,
        presenter: DetailsPresenter
    ) = PermissionsItemPresenter(resourceProvider, presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideScoresItemBlueprint(
        presenter: ScoresItemPresenter
    ): ItemBlueprint<*, *> = ScoresItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideScoresItemPresenter(
        presenter: DetailsPresenter
    ) = ScoresItemPresenter(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideRatingItemBlueprint(
        presenter: RatingItemPresenter
    ): ItemBlueprint<*, *> = RatingItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideRatingItemPresenter(
        @Named(DATE_FORMATTER) dateFormatter: DateFormat,
        locale: Locale,
        presenter: DetailsPresenter
    ) = RatingItemPresenter(dateFormatter, locale, presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideControlsItemBlueprint(
        presenter: ControlsItemPresenter
    ): ItemBlueprint<*, *> = ControlsItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideControlsItemPresenter(
        presenter: DetailsPresenter,
    ) = ControlsItemPresenter(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideUserRateItemBlueprint(
        presenter: UserRateItemPresenter
    ): ItemBlueprint<*, *> = UserRateItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideUserRateItemPresenter(
        presenter: DetailsPresenter,
        schedulers: SchedulersFactory,
    ) = UserRateItemPresenter(presenter, schedulers)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideUserReviewItemBlueprint(
        presenter: UserReviewItemPresenter
    ): ItemBlueprint<*, *> = UserReviewItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideUserReviewItemPresenter(
        @Named(DATE_FORMATTER) dateFormatter: DateFormat,
        locale: Locale,
        presenter: DetailsPresenter,
    ) = UserReviewItemPresenter(dateFormatter, locale, presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideDiscussItemBlueprint(
        presenter: DiscussItemPresenter
    ): ItemBlueprint<*, *> = DiscussItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideDiscussItemPresenter(
        presenter: DetailsPresenter,
    ) = DiscussItemPresenter(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideStatusItemBlueprint(
        presenter: StatusItemPresenter
    ): ItemBlueprint<*, *> = StatusItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideStatusItemPresenter(
        presenter: DetailsPresenter,
    ) = StatusItemPresenter(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideSecurityItemBlueprint(
        presenter: SecurityItemPresenter
    ): ItemBlueprint<*, *> = SecurityItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideSecurityItemPresenter(
        presenter: DetailsPresenter,
    ) = SecurityItemPresenter(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideScreenshotItemBlueprint(
        presenter: ScreenshotItemPresenter
    ): ItemBlueprint<*, *> = ScreenshotItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideScreenshotItemPresenter(
        presenter: ScreenshotsItemPresenter,
    ) = ScreenshotItemPresenter(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideScreenshotsItemBlueprint(
        presenter: ScreenshotsItemPresenter,
        @Named(SCREENSHOT_ADAPTER_PRESENTER) adapterPresenter: Lazy<AdapterPresenter>,
        binder: Lazy<ItemBinder>,
    ): ItemBlueprint<*, *> = ScreenshotsItemBlueprint(
        presenter,
        adapterPresenter,
        binder
    )

    @Provides
    @PerActivity
    internal fun provideScreenshotsItemPresenter(
        presenter: DetailsPresenter,
        @Named(SCREENSHOT_ADAPTER_PRESENTER) adapterPresenter: Lazy<AdapterPresenter>,
    ) = ScreenshotsItemPresenter(
        presenter,
        adapterPresenter
    )

}

const val DETAILS_ADAPTER_PRESENTER = "DetailsAdapterPresenter"
const val SCREENSHOT_ADAPTER_PRESENTER = "ScreenshotAdapterPresenter"