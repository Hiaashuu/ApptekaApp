package com.hiaashuu.appteka.di

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.google.gson.Gson
import com.hiaashuu.appteka.BuildConfig
import com.google.gson.GsonBuilder
import com.hiaashuu.appteka.analytics.EnvironmentProviderImpl
import com.hiaashuu.appteka.categories.CategoriesInteractor
import com.hiaashuu.appteka.categories.CategoriesInteractorImpl
import com.hiaashuu.appteka.core.ApiHolder
import com.hiaashuu.appteka.core.AppInfoProvider
import com.hiaashuu.appteka.core.AppInfoProviderImpl
import com.hiaashuu.appteka.core.BANANALYTICS_API_KEY
import com.hiaashuu.appteka.core.BANANALYTICS_URL
import com.hiaashuu.appteka.core.DeviceIdProvider
import com.hiaashuu.appteka.core.DeviceIdProviderImpl
import com.hiaashuu.appteka.core.HttpClientHolder
import com.hiaashuu.appteka.core.HttpClientHolderImpl
import com.hiaashuu.appteka.core.MigrationManager
import com.hiaashuu.appteka.core.MigrationManagerImpl
import com.hiaashuu.appteka.core.PackageInfoProvider
import com.hiaashuu.appteka.core.PackageInfoProviderImpl
import com.hiaashuu.appteka.core.PersistentCookieJar
import com.hiaashuu.appteka.core.ProxyChecker
import com.hiaashuu.appteka.core.ProxyCheckerImpl
import com.hiaashuu.appteka.core.ProxyConfigProvider
import com.hiaashuu.appteka.core.ProxyConfigProviderImpl
import com.hiaashuu.appteka.core.StandByApi
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.core.StreamsProvider
import com.hiaashuu.appteka.core.permissions.UserCapabilitiesInteractor
import com.hiaashuu.appteka.core.permissions.UserCapabilitiesInteractorImpl
import com.hiaashuu.appteka.core.permissions.UserCapabilitiesProvider
import com.hiaashuu.appteka.core.permissions.UserCapabilitiesProviderImpl
import com.hiaashuu.appteka.core.StreamsProviderImpl
import com.hiaashuu.appteka.core.TimeProvider
import com.hiaashuu.appteka.core.TimeProviderImpl
import com.hiaashuu.appteka.core.UserAgentProvider
import com.hiaashuu.appteka.core.UserAgentProviderImpl
import com.hiaashuu.appteka.download.ApkStorage
import com.hiaashuu.appteka.download.DownloadManager
import com.hiaashuu.appteka.download.DownloadManagerImpl
import com.hiaashuu.appteka.download.DownloadNotifications
import com.hiaashuu.appteka.download.DownloadNotificationsImpl
import com.hiaashuu.appteka.download.LegacyApkStorage
import com.hiaashuu.appteka.download.MediaStoreApkStorage
import com.hiaashuu.appteka.screen.details.DetailsDeepLinkParser
import com.hiaashuu.appteka.screen.details.DetailsDeepLinkParserImpl
import com.hiaashuu.appteka.screen.profile.ProfileDeepLinkParser
import com.hiaashuu.appteka.screen.profile.ProfileDeepLinkParserImpl
import com.hiaashuu.appteka.upload.UploadManager
import com.hiaashuu.appteka.upload.UploadManagerImpl
import com.hiaashuu.appteka.upload.UploadNotifications
import com.hiaashuu.appteka.upload.UploadNotificationsImpl
import com.hiaashuu.appteka.user.ModerationProvider
import com.hiaashuu.appteka.user.ModerationProviderImpl
import com.hiaashuu.appteka.Appteka
import com.hiaashuu.appteka.util.Analytics
import com.hiaashuu.appteka.util.AnalyticsImpl
import com.hiaashuu.appteka.util.Logger
import com.hiaashuu.appteka.util.LoggerImpl
import com.hiaashuu.appteka.util.PackageObserver
import com.hiaashuu.appteka.util.PackageObserverImpl
import com.hiaashuu.appteka.util.SchedulersFactory
import com.hiaashuu.appteka.util.SchedulersFactoryImpl
import com.hiaashuu.appteka.util.ThemeManager
import com.tomclaw.bananalytics.Bananalytics
import com.tomclaw.bananalytics.BananalyticsConfig
import com.tomclaw.bananalytics.BananalyticsImpl
import com.tomclaw.bananalytics.EnvironmentProvider
import com.tomclaw.cache.DiskLruCache
import dagger.Module
import dagger.Provides
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    internal fun provideContext(): Context = app

    @Provides
    @Singleton
    internal fun provideThemeManager(): ThemeManager = (app as Appteka).themeManager

    @Provides
    @Singleton
    internal fun provideSchedulersFactory(): SchedulersFactory = SchedulersFactoryImpl()

    @Provides
    @Singleton
    @Named(USER_DIR)
    fun provideFilesDir(): File = app.filesDir

    @Provides
    @Singleton
    @Named(PICKED_MEDIA_CACHE)
    internal fun providePickedMediaCache(context: Context): DiskLruCache {
        val dir = File(context.cacheDir, "picked_media").apply { mkdirs() }
        return DiskLruCache.create(dir, PICKED_MEDIA_CACHE_SIZE)
    }

    @Provides
    @Singleton
    internal fun provideApkStorage(context: Context): ApkStorage {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStoreApkStorage(context)
        } else {
            LegacyApkStorage(context)
        }
    }

    @Provides
    @Singleton
    internal fun provideLogger(): Logger = LoggerImpl()

    @Provides
    @Singleton
    internal fun provideLocale(): Locale = Locale.getDefault()

    @Provides
    @Singleton
    @Named(TIME_FORMATTER)
    internal fun provideTimeFormatter(locale: Locale): DateFormat =
        SimpleDateFormat("HH:mm", locale)

    @Provides
    @Singleton
    @Named(DATE_FORMATTER)
    internal fun provideDateFormatter(locale: Locale): DateFormat =
        SimpleDateFormat("dd.MM.yy", locale)

    @Provides
    @Singleton
    internal fun provideTimeProvider(context: Context): TimeProvider = TimeProviderImpl(context)

    @Provides
    @Singleton
    internal fun provideUserAgentProvider(
        appInfoProvider: AppInfoProvider,
        locale: Locale
    ): UserAgentProvider = UserAgentProviderImpl(appInfoProvider, locale)

    @Provides
    @Singleton
    internal fun provideDeviceIdProvider(
        @Named(USER_DIR) filesDir: File
    ): DeviceIdProvider =
        DeviceIdProviderImpl(app, filesDir)

    @Provides
    @Singleton
    internal fun provideAppInfoProvider(
        packageManager: PackageManager,
        locale: Locale
    ): AppInfoProvider = AppInfoProviderImpl(app, packageManager, locale)

    @Provides
    @Singleton
    internal fun provideMigrationManager(appInfoProvider: AppInfoProvider): MigrationManager =
        MigrationManagerImpl(app, appInfoProvider)

    @Provides
    @Singleton
    internal fun provideEnvironmentProvider(
        locale: Locale,
        appInfoProvider: AppInfoProvider,
        deviceIdProvider: DeviceIdProvider,
    ): EnvironmentProvider = EnvironmentProviderImpl(locale, appInfoProvider, deviceIdProvider)

    @Provides
    @Singleton
    internal fun provideBananalyticsConfig(): BananalyticsConfig = BananalyticsConfig(
        baseUrl = BANANALYTICS_URL,
        apiKey = BANANALYTICS_API_KEY,
    )

    @Provides
    @Singleton
    internal fun provideBananalytics(
        @Named(USER_DIR) filesDir: File,
        config: BananalyticsConfig,
        environmentProvider: EnvironmentProvider,
    ): Bananalytics = BananalyticsImpl(
        filesDir = filesDir,
        config = config,
        environmentProvider = environmentProvider,
        isDebug = BuildConfig.DEBUG
    )

    @Provides
    @Singleton
    internal fun provideDownloadNotifications(analytics: Analytics): DownloadNotifications =
        DownloadNotificationsImpl(app, analytics)

    @Provides
    @Singleton
    internal fun provideUploadNotifications(): UploadNotifications =
        UploadNotificationsImpl(app)

    @Provides
    @Singleton
    internal fun provideModerationProvider(): ModerationProvider = ModerationProviderImpl()

    @Provides
    @Singleton
    internal fun provideUserCapabilitiesProvider(): UserCapabilitiesProvider =
        UserCapabilitiesProviderImpl()

    @Provides
    @Singleton
    internal fun provideUserCapabilitiesInteractor(
        api: StoreApi,
        provider: UserCapabilitiesProvider,
        schedulers: SchedulersFactory,
    ): UserCapabilitiesInteractor =
        UserCapabilitiesInteractorImpl(api, provider, schedulers)

    @Provides
    @Singleton
    internal fun provideCategoriesInteractor(
        api: StoreApi,
        schedulers: SchedulersFactory
    ): CategoriesInteractor = CategoriesInteractorImpl(api, schedulers)

    @Provides
    @Singleton
    internal fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    internal fun provideAnalytics(
        bananalytics: Bananalytics
    ): Analytics = AnalyticsImpl(bananalytics)

    @Provides
    @Singleton
    internal fun providePackageObserver(
        context: Context
    ): PackageObserver = PackageObserverImpl(context, context.packageManager)

    @Provides
    @Singleton
    internal fun provideManager(
        context: Context
    ): PackageManager = context.packageManager

    @Provides
    @Singleton
    internal fun provideDownloadManager(
        apkStorage: ApkStorage,
        cookieJar: CookieJar,
        proxyConfigProvider: ProxyConfigProvider,
    ): DownloadManager = DownloadManagerImpl(apkStorage, cookieJar, proxyConfigProvider)

    @Provides
    @Singleton
    internal fun provideUploadManager(
        context: Context,
        cookieJar: CookieJar,
        proxyConfigProvider: ProxyConfigProvider,
        api: StoreApi,
        gson: Gson
    ): UploadManager = UploadManagerImpl(context, cookieJar, proxyConfigProvider, api, gson)

    @Provides
    @Singleton
    internal fun provideCookieJar(
        @Named(USER_DIR) filesDir: File,
    ): CookieJar = PersistentCookieJar(filesDir)

    @Provides
    @Singleton
    internal fun provideProxyConfigProvider(): ProxyConfigProvider =
        ProxyConfigProviderImpl(app)

    @Provides
    @Singleton
    internal fun provideProxyChecker(): ProxyChecker = ProxyCheckerImpl()

    @Provides
    @Singleton
    internal fun provideHttpClientHolder(
        cookieJar: CookieJar,
        userAgentProvider: UserAgentProvider,
        deviceIdProvider: DeviceIdProvider,
        appInfoProvider: AppInfoProvider,
        proxyConfigProvider: ProxyConfigProvider,
    ): HttpClientHolderImpl = HttpClientHolderImpl(
        app = app,
        cookieJar = cookieJar,
        userAgentProvider = userAgentProvider,
        deviceIdProvider = deviceIdProvider,
        appInfoProvider = appInfoProvider,
        proxyConfigProvider = proxyConfigProvider
    )

    @Provides
    @Singleton
    internal fun provideHttpClientHolderInterface(
        holder: HttpClientHolderImpl
    ): HttpClientHolder = holder

    @Provides
    @Singleton
    internal fun provideOkHttpClient(holder: HttpClientHolder): OkHttpClient = holder.getClient()

    @Provides
    @Singleton
    internal fun provideApiHolder(
        httpClientHolder: HttpClientHolder,
        gson: Gson
    ): ApiHolder = ApiHolder(httpClientHolder, gson)

    @Provides
    @Singleton
    internal fun provideStoreApi(apiHolder: ApiHolder): StoreApi = apiHolder.storeApi

    @Provides
    @Singleton
    internal fun provideStandByApi(apiHolder: ApiHolder): StandByApi = apiHolder.standByApi

    @Provides
    @Singleton
    internal fun provideStreamsProvider(httpClientHolder: HttpClientHolder): StreamsProvider =
        StreamsProviderImpl(context = app, httpClientHolder)

    @Provides
    @Singleton
    internal fun providePackageInfoProvider(packageManager: PackageManager): PackageInfoProvider =
        PackageInfoProviderImpl(packageManager)

    @Provides
    @Singleton
    internal fun provideDetailsDeepLinkParser(): DetailsDeepLinkParser = DetailsDeepLinkParserImpl()

    @Provides
    @Singleton
    internal fun provideProfileDeepLinkParser(): ProfileDeepLinkParser = ProfileDeepLinkParserImpl()

}

const val TIME_FORMATTER = "TimeFormatter"
const val DATE_FORMATTER = "DateFormatter"
const val USER_DIR = "user"
const val PICKED_MEDIA_CACHE = "PickedMediaCache"

private const val PICKED_MEDIA_CACHE_SIZE = 10L * 1024 * 1024