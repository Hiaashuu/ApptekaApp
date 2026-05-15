package com.hiaashuu.appteka.screen.post.di

import android.content.Context
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.post.PostConverter
import com.hiaashuu.appteka.screen.post.PostConverterImpl
import com.hiaashuu.appteka.screen.post.PostInteractor
import com.hiaashuu.appteka.screen.post.PostInteractorImpl
import com.hiaashuu.appteka.screen.post.PostPreferencesProvider
import com.hiaashuu.appteka.screen.post.PostPreferencesProviderImpl
import com.hiaashuu.appteka.screen.post.PostPresenter
import com.hiaashuu.appteka.screen.post.PostPresenterImpl
import com.hiaashuu.appteka.util.ImageCompressor
import com.hiaashuu.appteka.util.ImageCompressorImpl
import com.hiaashuu.appteka.screen.post.adapter.append.AppendItemBlueprint
import com.hiaashuu.appteka.screen.post.adapter.append.AppendItemPresenter
import com.hiaashuu.appteka.screen.post.adapter.image.ImageItemBlueprint
import com.hiaashuu.appteka.screen.post.adapter.image.ImageItemPresenter
import com.hiaashuu.appteka.screen.post.adapter.reactions.ReactionsItemBlueprint
import com.hiaashuu.appteka.screen.post.adapter.reactions.ReactionsItemPresenter
import com.hiaashuu.appteka.screen.post.adapter.ribbon.RibbonItemBlueprint
import com.hiaashuu.appteka.screen.post.adapter.ribbon.RibbonItemPresenter
import com.hiaashuu.appteka.screen.post.adapter.submit.SubmitItemBlueprint
import com.hiaashuu.appteka.screen.post.adapter.submit.SubmitItemPresenter
import com.hiaashuu.appteka.screen.post.adapter.text.TextItemBlueprint
import com.hiaashuu.appteka.screen.post.adapter.text.TextItemPresenter
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Named

@Module
class PostModule(
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: PostInteractor,
        postConverter: PostConverter,
        @Named(POST_ADAPTER_PRESENTER) adapterPresenter: Lazy<AdapterPresenter>,
        preferences: PostPreferencesProvider,
        schedulers: SchedulersFactory
    ): PostPresenter = PostPresenterImpl(
        interactor,
        postConverter,
        adapterPresenter,
        preferences,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        api: StoreApi,
        compressor: ImageCompressor,
        schedulers: SchedulersFactory
    ): PostInteractor = PostInteractorImpl(api, compressor, schedulers)

    @Provides
    @PerActivity
    internal fun provideScreenshotCompressor(): ImageCompressor {
        return ImageCompressorImpl(context.contentResolver)
    }

    @Provides
    @PerActivity
    internal fun providePreferencesProvider(): PostPreferencesProvider {
        return PostPreferencesProviderImpl(context)
    }

    @Provides
    @PerActivity
    internal fun providePostConverterProvider(): PostConverter {
        return PostConverterImpl()
    }

    @Provides
    @PerActivity
    @Named(POST_ADAPTER_PRESENTER)
    internal fun providePostAdapterPresenter(binder: ItemBinder): AdapterPresenter {
        return SimpleAdapterPresenter(binder)
    }

    @Provides
    @PerActivity
    @Named(SCREENSHOT_ADAPTER_PRESENTER)
    internal fun provideScreenshotAdapterPresenter(binder: ItemBinder): AdapterPresenter {
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
    internal fun provideScreenshotsItemBlueprint(
        presenter: RibbonItemPresenter,
        @Named(SCREENSHOT_ADAPTER_PRESENTER) adapterPresenter: Lazy<AdapterPresenter>,
        binder: Lazy<ItemBinder>,
    ): ItemBlueprint<*, *> = RibbonItemBlueprint(presenter, adapterPresenter, binder)

    @Provides
    @PerActivity
    internal fun provideScreenshotsItemPresenter(
        presenter: PostPresenter,
        @Named(SCREENSHOT_ADAPTER_PRESENTER) adapterPresenter: Lazy<AdapterPresenter>,
    ) = RibbonItemPresenter(presenter, adapterPresenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideScreenAppendItemBlueprint(
        presenter: AppendItemPresenter
    ): ItemBlueprint<*, *> = AppendItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideScreenAppendItemPresenter(
        presenter: PostPresenter
    ) = AppendItemPresenter(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideScreenImageItemBlueprint(
        presenter: ImageItemPresenter
    ): ItemBlueprint<*, *> = ImageItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideScreenImageItemPresenter(
        presenter: PostPresenter
    ) = ImageItemPresenter(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideTextItemBlueprint(
        presenter: TextItemPresenter
    ): ItemBlueprint<*, *> = TextItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideTextItemPresenter(
        presenter: PostPresenter
    ) = TextItemPresenter(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideSubmitItemBlueprint(
        presenter: SubmitItemPresenter
    ): ItemBlueprint<*, *> = SubmitItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideSubmitItemPresenter(
        presenter: PostPresenter
    ) = SubmitItemPresenter(presenter)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideReactionsItemBlueprint(
        presenter: ReactionsItemPresenter,
    ): ItemBlueprint<*, *> = ReactionsItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideReactionsItemPresenter(
        presenter: PostPresenter
    ) = ReactionsItemPresenter(presenter)

}

const val POST_ADAPTER_PRESENTER = "PostAdapterPresenter"
const val SCREENSHOT_ADAPTER_PRESENTER = "ScreenshotAdapterPresenter"