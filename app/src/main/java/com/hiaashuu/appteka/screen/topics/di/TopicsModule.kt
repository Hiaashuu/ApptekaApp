package com.hiaashuu.appteka.screen.topics.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.topics.TopicConverter
import com.hiaashuu.appteka.screen.topics.TopicConverterImpl
import com.hiaashuu.appteka.screen.topics.TopicsInteractor
import com.hiaashuu.appteka.screen.topics.TopicsInteractorImpl
import com.hiaashuu.appteka.screen.topics.TopicsPreferencesProvider
import com.hiaashuu.appteka.screen.topics.TopicsPreferencesProviderImpl
import com.hiaashuu.appteka.screen.topics.TopicsPresenter
import com.hiaashuu.appteka.screen.topics.TopicsPresenterImpl
import com.hiaashuu.appteka.screen.topics.TopicsResourceProvider
import com.hiaashuu.appteka.screen.topics.TopicsResourceProviderImpl
import com.hiaashuu.appteka.screen.topics.adapter.topic.TopicItemBlueprint
import com.hiaashuu.appteka.screen.topics.adapter.topic.TopicItemPresenter
import com.hiaashuu.appteka.util.PerFragment
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.Locale

@Module
class TopicsModule(
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerFragment
    internal fun providePresenter(
        converter: TopicConverter,
        preferences: TopicsPreferencesProvider,
        topicsInteractor: TopicsInteractor,
        adapterPresenter: Lazy<AdapterPresenter>,
        schedulers: SchedulersFactory
    ): TopicsPresenter = TopicsPresenterImpl(
        converter,
        preferences,
        topicsInteractor,
        adapterPresenter,
        schedulers,
        state
    )

    @Provides
    @PerFragment
    internal fun provideInteractor(
        api: StoreApi,
        locale: Locale,
        schedulers: SchedulersFactory
    ): TopicsInteractor = TopicsInteractorImpl(api, locale, schedulers)

    @Provides
    @PerFragment
    internal fun providePreferencesProvider(): TopicsPreferencesProvider =
        TopicsPreferencesProviderImpl(context)

    @Provides
    @PerFragment
    internal fun provideTopicConverter(
        resourceProvider: TopicsResourceProvider
    ): TopicConverter = TopicConverterImpl(resourceProvider)

    @Provides
    @PerFragment
    internal fun provideResourceProvider(): TopicsResourceProvider =
        TopicsResourceProviderImpl(context.resources)

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
    internal fun provideTopicItemBlueprint(
        presenter: TopicItemPresenter
    ): ItemBlueprint<*, *> = TopicItemBlueprint(presenter)

    @Provides
    @PerFragment
    internal fun provideTopicItemPresenter(presenter: TopicsPresenter) =
        TopicItemPresenter(presenter)

}