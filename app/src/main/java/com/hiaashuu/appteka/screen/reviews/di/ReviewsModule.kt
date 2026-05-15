package com.hiaashuu.appteka.screen.reviews.di

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
import com.hiaashuu.appteka.screen.reviews.ReviewConverter
import com.hiaashuu.appteka.screen.reviews.ReviewConverterImpl
import com.hiaashuu.appteka.screen.reviews.ReviewsInteractor
import com.hiaashuu.appteka.screen.reviews.ReviewsInteractorImpl
import com.hiaashuu.appteka.screen.reviews.ReviewsPreferencesProvider
import com.hiaashuu.appteka.screen.reviews.ReviewsPreferencesProviderImpl
import com.hiaashuu.appteka.screen.reviews.ReviewsPresenter
import com.hiaashuu.appteka.screen.reviews.ReviewsPresenterImpl
import com.hiaashuu.appteka.screen.reviews.adapter.review.ReviewItemBlueprint
import com.hiaashuu.appteka.screen.reviews.adapter.review.ReviewItemPresenter
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
class ReviewsModule(
    private val context: Context,
    private val userId: Int,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: ReviewsInteractor,
        adapterPresenter: Lazy<AdapterPresenter>,
        converter: ReviewConverter,
        schedulers: SchedulersFactory
    ): ReviewsPresenter = ReviewsPresenterImpl(
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
        locale: Locale,
        schedulers: SchedulersFactory
    ): ReviewsInteractor = ReviewsInteractorImpl(
        api,
        userId,
        locale,
        schedulers
    )

    @Provides
    @PerActivity
    internal fun provideReviewConverter(): ReviewConverter {
        return ReviewConverterImpl()
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
    internal fun provideReviewsPreferencesProvider(): ReviewsPreferencesProvider {
        return ReviewsPreferencesProviderImpl(context)
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
    internal fun provideReviewItemBlueprint(
        presenter: ReviewItemPresenter,
        preferences: ReviewsPreferencesProvider,
    ): ItemBlueprint<*, *> = ReviewItemBlueprint(presenter, preferences)

    @Provides
    @PerActivity
    internal fun provideReviewItemPresenter(
        @Named(DATE_FORMATTER) dateFormatter: DateFormat,
        presenter: ReviewsPresenter,
    ) = ReviewItemPresenter(dateFormatter, presenter)

}