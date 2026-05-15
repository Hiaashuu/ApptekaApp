package com.hiaashuu.appteka.screen.ratings.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.categories.CategoryConverter
import com.hiaashuu.appteka.categories.CategoryConverterImpl
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.di.DATE_FORMATTER
import com.hiaashuu.appteka.screen.ratings.RatingConverter
import com.hiaashuu.appteka.screen.ratings.RatingConverterImpl
import com.hiaashuu.appteka.screen.ratings.RatingsInteractor
import com.hiaashuu.appteka.screen.ratings.RatingsInteractorImpl
import com.hiaashuu.appteka.screen.ratings.RatingsPreferencesProvider
import com.hiaashuu.appteka.screen.ratings.RatingsPreferencesProviderImpl
import com.hiaashuu.appteka.screen.ratings.RatingsPresenter
import com.hiaashuu.appteka.screen.ratings.RatingsPresenterImpl
import com.hiaashuu.appteka.screen.ratings.adapter.rating.RatingItemBlueprint
import com.hiaashuu.appteka.screen.ratings.adapter.rating.RatingItemPresenter
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.text.DateFormat
import java.util.Locale
import javax.inject.Named

@Module
class RatingsModule(
    private val context: Context,
    private val appId: String,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: RatingsInteractor,
        adapterPresenter: Lazy<AdapterPresenter>,
        converter: RatingConverter,
        schedulers: SchedulersFactory
    ): RatingsPresenter = RatingsPresenterImpl(
        interactor,
        adapterPresenter,
        converter,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        schedulers: SchedulersFactory
    ): RatingsInteractor = RatingsInteractorImpl(
        api,
        appId,
        schedulers
    )

    @Provides
    @PerActivity
    internal fun provideRatingConverter(): RatingConverter {
        return RatingConverterImpl()
    }

    @Provides
    @PerActivity
    internal fun provideCategoryConverter(locale: Locale): CategoryConverter =
        CategoryConverterImpl(locale)

    @Provides
    @PerActivity
    internal fun provideRatingsPreferencesProvider(): RatingsPreferencesProvider =
        RatingsPreferencesProviderImpl(context)

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
    internal fun provideRatingItemBlueprint(
        presenter: RatingItemPresenter,
        preferences: RatingsPreferencesProvider,
    ): ItemBlueprint<*, *> = RatingItemBlueprint(presenter, preferences)

    @Provides
    @PerActivity
    internal fun provideRatingItemPresenter(
        @Named(DATE_FORMATTER) dateFormatter: DateFormat,
        locale: Locale,
        presenter: RatingsPresenter,
    ) = RatingItemPresenter(dateFormatter, locale, presenter)

}