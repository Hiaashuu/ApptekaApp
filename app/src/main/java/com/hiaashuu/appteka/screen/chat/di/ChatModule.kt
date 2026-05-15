package com.hiaashuu.appteka.screen.chat.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.core.permissions.UserCapabilitiesProvider
import com.hiaashuu.appteka.di.DATE_FORMATTER
import com.hiaashuu.appteka.di.TIME_FORMATTER
import com.hiaashuu.appteka.dto.TopicEntity
import com.hiaashuu.appteka.screen.chat.ChatInteractor
import com.hiaashuu.appteka.screen.chat.ChatInteractorImpl
import com.hiaashuu.appteka.screen.chat.ChatPreferencesProvider
import com.hiaashuu.appteka.screen.chat.ChatPreferencesProviderImpl
import com.hiaashuu.appteka.screen.chat.ChatPresenter
import com.hiaashuu.appteka.screen.chat.ChatPresenterImpl
import com.hiaashuu.appteka.screen.chat.ChatResourceProvider
import com.hiaashuu.appteka.screen.chat.ChatResourceProviderImpl
import com.hiaashuu.appteka.screen.chat.MessageConverter
import com.hiaashuu.appteka.screen.chat.MessageConverterImpl
import com.hiaashuu.appteka.screen.chat.adapter.incoming.IncomingMsgItemBlueprint
import com.hiaashuu.appteka.screen.chat.adapter.incoming.IncomingMsgItemPresenter
import com.hiaashuu.appteka.screen.chat.adapter.outgoing.OutgoingMsgItemBlueprint
import com.hiaashuu.appteka.screen.chat.adapter.outgoing.OutgoingMsgItemPresenter
import com.hiaashuu.appteka.screen.chat.adapter.system.SystemMsgItemBlueprint
import com.hiaashuu.appteka.screen.chat.adapter.system.SystemMsgItemPresenter
import com.hiaashuu.appteka.util.ImageCompressor
import com.hiaashuu.appteka.util.ImageCompressorImpl
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
class ChatModule(
    private val context: Context,
    private val topicEntity: TopicEntity?,
    private val topicId: Int,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        bananalytics: Bananalytics,
        converter: MessageConverter,
        chatInteractor: ChatInteractor,
        resourceProvider: ChatResourceProvider,
        capabilitiesProvider: UserCapabilitiesProvider,
        adapterPresenter: Lazy<AdapterPresenter>,
        schedulers: SchedulersFactory
    ): ChatPresenter = ChatPresenterImpl(
        topicEntity,
        topicId,
        bananalytics,
        converter,
        chatInteractor,
        resourceProvider,
        capabilitiesProvider,
        adapterPresenter,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        compressor: ImageCompressor,
        locale: Locale,
        schedulers: SchedulersFactory
    ): ChatInteractor = ChatInteractorImpl(api, compressor, locale, schedulers)

    @Provides
    @PerActivity
    internal fun provideImageCompressor(): ImageCompressor =
        ImageCompressorImpl(context.contentResolver)

    @Provides
    @PerActivity
    internal fun provideAdapterPresenter(binder: ItemBinder): AdapterPresenter {
        return SimpleAdapterPresenter(binder)
    }

    @Provides
    @PerActivity
    internal fun provideMessageConverter(
        @Named(TIME_FORMATTER) timeFormatter: DateFormat,
        @Named(DATE_FORMATTER) dateFormatter: DateFormat,
        resourceProvider: ChatResourceProvider
    ): MessageConverter = MessageConverterImpl(timeFormatter, dateFormatter, resourceProvider)

    @Provides
    @PerActivity
    internal fun provideResourceProvider(): ChatResourceProvider =
        ChatResourceProviderImpl(context.resources)

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
    internal fun provideIncomingMsgItemBlueprint(
        presenter: IncomingMsgItemPresenter
    ): ItemBlueprint<*, *> = IncomingMsgItemBlueprint(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideOutgoingMsgItemBlueprint(
        presenter: OutgoingMsgItemPresenter
    ): ItemBlueprint<*, *> = OutgoingMsgItemBlueprint(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideSystemMsgItemBlueprint(
        presenter: SystemMsgItemPresenter
    ): ItemBlueprint<*, *> = SystemMsgItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideIncomingMsgItemPresenter(
        locale: Locale,
        presenter: ChatPresenter,
    ) = IncomingMsgItemPresenter(locale, presenter)

    @Provides
    @PerActivity
    internal fun provideOutgoingMsgItemPresenter(
        presenter: ChatPresenter
    ) = OutgoingMsgItemPresenter(presenter)

    @Provides
    @PerActivity
    internal fun provideSystemMsgItemPresenter() = SystemMsgItemPresenter()

    @Provides
    @PerActivity
    internal fun provideChatPreferencesProvider(): ChatPreferencesProvider =
        ChatPreferencesProviderImpl(context)

}