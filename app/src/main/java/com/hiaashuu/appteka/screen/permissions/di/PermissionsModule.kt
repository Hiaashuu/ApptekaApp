package com.hiaashuu.appteka.screen.permissions.di

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleAdapterPresenter
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.screen.permissions.PermissionInfoProvider
import com.hiaashuu.appteka.screen.permissions.PermissionInfoProviderImpl
import com.hiaashuu.appteka.screen.permissions.PermissionsConverter
import com.hiaashuu.appteka.screen.permissions.PermissionsConverterImpl
import com.hiaashuu.appteka.screen.permissions.PermissionsPresenter
import com.hiaashuu.appteka.screen.permissions.PermissionsPresenterImpl
import com.hiaashuu.appteka.screen.permissions.PermissionsResourceProvider
import com.hiaashuu.appteka.screen.permissions.PermissionsResourceProviderImpl
import com.hiaashuu.appteka.screen.permissions.adapter.safe.SafePermissionItemBlueprint
import com.hiaashuu.appteka.screen.permissions.adapter.safe.SafePermissionItemPresenter
import com.hiaashuu.appteka.screen.permissions.adapter.unsafe.UnsafePermissionItemBlueprint
import com.hiaashuu.appteka.screen.permissions.adapter.unsafe.UnsafePermissionItemPresenter
import com.hiaashuu.appteka.util.PerActivity
import com.hiaashuu.appteka.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.Locale

@Module
class PermissionsModule(
    private val context: Context,
    private val permissions: List<String>,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        adapterPresenter: Lazy<AdapterPresenter>,
        converter: PermissionsConverter,
        schedulers: SchedulersFactory
    ): PermissionsPresenter =
        PermissionsPresenterImpl(permissions, adapterPresenter, converter, schedulers, state)

    @Provides
    @PerActivity
    internal fun providePermissionInfoProvider(
        packageManager: PackageManager,
        locale: Locale
    ): PermissionInfoProvider {
        return PermissionInfoProviderImpl(packageManager, locale)
    }

    @Provides
    @PerActivity
    internal fun providePermissionsResourceProvider(): PermissionsResourceProvider {
        return PermissionsResourceProviderImpl(context.resources)
    }

    @Provides
    @PerActivity
    internal fun providePermissionsConverter(
        permissionInfoProvider: PermissionInfoProvider
    ): PermissionsConverter {
        return PermissionsConverterImpl(permissionInfoProvider)
    }

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
    internal fun provideSafeItemBlueprint(
        presenter: SafePermissionItemPresenter
    ): ItemBlueprint<*, *> = SafePermissionItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideSafePermissionItemPresenter(
        resourceProvider: PermissionsResourceProvider
    ) = SafePermissionItemPresenter(resourceProvider)

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideUnsafePermissionItemBlueprint(
        presenter: UnsafePermissionItemPresenter
    ): ItemBlueprint<*, *> = UnsafePermissionItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideUnsafePermissionItemPresenter(
        resourceProvider: PermissionsResourceProvider
    ) = UnsafePermissionItemPresenter(resourceProvider)

}