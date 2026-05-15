package com.hiaashuu.appteka.screen.users.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.categories.CategoryConverter
import com.hiaashuu.appteka.categories.CategoryConverterImpl
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.core.TimeProvider
import com.hiaashuu.appteka.screen.users.PublishersInteractor
import com.hiaashuu.appteka.screen.users.UsersInteractor
import com.hiaashuu.appteka.screen.users.SubscribersInteractor
import com.hiaashuu.appteka.screen.users.UsersPresenter
import com.hiaashuu.appteka.screen.users.UsersPresenterImpl
import com.hiaashuu.appteka.screen.users.UserConverter
import com.hiaashuu.appteka.screen.users.UserConverterImpl
import com.hiaashuu.appteka.screen.users.UsersType
import com.hiaashuu.appteka.screen.users.adapter.subscriber.SubscriberItemBlueprint
import com.hiaashuu.appteka.screen.users.adapter.subscriber.SubscriberItemPresenter
import com.hiaashuu.appteka.screen.users.adapter.UsersResourceProvider
import com.hiaashuu.appteka.screen.users.adapter.UsersResourceProviderImpl
import com.hiaashuu.appteka.util.PerFragment
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.Locale

@Module
class SubscribersModule(
    private val context: Context,
    private val type: UsersType,
    private val userId: Int,
    private val state: Bundle?
) {

    @Provides
    @PerFragment
    internal fun providePresenter(
        interactor: UsersInteractor,
        adapterPresenter: Lazy<AdapterPresenter>,
        converter: UserConverter,
        schedulers: SchedulersFactory
    ): UsersPresenter = UsersPresenterImpl(
        userId,
        interactor,
        adapterPresenter,
        converter,
        schedulers,
        state
    )

    @Provides
    @PerFragment
    internal fun provideInteractor(
        api: StoreApi,
        schedulers: SchedulersFactory
    ): UsersInteractor = when (type) {
        UsersType.SUBSCRIBERS -> SubscribersInteractor(api, schedulers)
        UsersType.PUBLISHERS -> PublishersInteractor(api, schedulers)
    }

    @Provides
    @PerFragment
    internal fun provideUserConverter(): UserConverter {
        return UserConverterImpl()
    }

    @Provides
    @PerFragment
    internal fun provideCategoryConverter(locale: Locale): CategoryConverter =
        CategoryConverterImpl(locale)

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
    internal fun provideUserItemBlueprint(
        presenter: SubscriberItemPresenter
    ): ItemBlueprint<*, *> = SubscriberItemBlueprint(presenter)

    @Provides
    @PerFragment
    internal fun provideAppItemPresenter(
        locale: Locale,
        resourceProvider: UsersResourceProvider,
        presenter: UsersPresenter
    ) = SubscriberItemPresenter(locale, resourceProvider, presenter)

    @Provides
    @PerFragment
    internal fun provideSubscriberResourceProvider(timeProvider: TimeProvider): UsersResourceProvider {
        return UsersResourceProviderImpl(context, timeProvider)
    }

}