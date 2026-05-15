package com.hiaashuu.appteka.di

import com.hiaashuu.appteka.core.MigrationManager
import com.hiaashuu.appteka.screen.details.DetailsDeepLinkParser
import com.hiaashuu.appteka.screen.profile.ProfileDeepLinkParser
import com.hiaashuu.appteka.download.di.DownloadServiceComponent
import com.hiaashuu.appteka.download.di.DownloadServiceModule
import com.hiaashuu.appteka.util.Analytics
import com.tomclaw.bananalytics.Bananalytics
import com.hiaashuu.appteka.screen.about.di.AboutComponent
import com.hiaashuu.appteka.screen.about.di.AboutModule
import com.hiaashuu.appteka.screen.agreement.di.AgreementComponent
import com.hiaashuu.appteka.screen.agreement.di.AgreementModule
import com.hiaashuu.appteka.screen.avatar_crop.di.AvatarCropComponent
import com.hiaashuu.appteka.screen.avatar_crop.di.AvatarCropModule
import com.hiaashuu.appteka.screen.bdui.di.BduiScreenComponent
import com.hiaashuu.appteka.screen.bdui.di.BduiScreenModule
import com.hiaashuu.appteka.screen.auth.request_code.di.RequestCodeComponent
import com.hiaashuu.appteka.screen.auth.request_code.di.RequestCodeModule
import com.hiaashuu.appteka.screen.auth.verify_code.di.VerifyCodeComponent
import com.hiaashuu.appteka.screen.auth.verify_code.di.VerifyCodeModule
import com.hiaashuu.appteka.screen.change_email.di.ChangeEmailComponent
import com.hiaashuu.appteka.screen.change_email.di.ChangeEmailModule
import com.hiaashuu.appteka.screen.chat.di.ChatComponent
import com.hiaashuu.appteka.screen.chat.di.ChatModule
import com.hiaashuu.appteka.screen.create_chat.di.CreateChatComponent
import com.hiaashuu.appteka.screen.create_chat.di.CreateChatModule
import com.hiaashuu.appteka.screen.details.di.DetailsComponent
import com.hiaashuu.appteka.screen.details.di.DetailsModule
import com.hiaashuu.appteka.screen.distro.di.DistroComponent
import com.hiaashuu.appteka.screen.distro.di.DistroModule
import com.hiaashuu.appteka.screen.downloads.di.DownloadsComponent
import com.hiaashuu.appteka.screen.downloads.di.DownloadsModule
import com.hiaashuu.appteka.screen.edit_profile.di.EditProfileComponent
import com.hiaashuu.appteka.screen.edit_profile.di.EditProfileModule
import com.hiaashuu.appteka.screen.favorite.di.FavoriteComponent
import com.hiaashuu.appteka.screen.favorite.di.FavoriteModule
import com.hiaashuu.appteka.screen.feed.di.FeedComponent
import com.hiaashuu.appteka.screen.feed.di.FeedModule
import com.hiaashuu.appteka.screen.gallery.di.GalleryComponent
import com.hiaashuu.appteka.screen.gallery.di.GalleryModule
import com.hiaashuu.appteka.screen.home.di.HomeComponent
import com.hiaashuu.appteka.screen.home.di.HomeModule
import com.hiaashuu.appteka.screen.installed.di.InstalledComponent
import com.hiaashuu.appteka.screen.installed.di.InstalledModule
import com.hiaashuu.appteka.screen.moderation.di.ModerationComponent
import com.hiaashuu.appteka.screen.moderation.di.ModerationModule
import com.hiaashuu.appteka.screen.permissions.di.PermissionsComponent
import com.hiaashuu.appteka.screen.permissions.di.PermissionsModule
import com.hiaashuu.appteka.screen.post.di.PostComponent
import com.hiaashuu.appteka.screen.post.di.PostModule
import com.hiaashuu.appteka.screen.profile.di.ProfileComponent
import com.hiaashuu.appteka.screen.profile.di.ProfileModule
import com.hiaashuu.appteka.screen.rate.di.RateComponent
import com.hiaashuu.appteka.screen.rate.di.RateModule
import com.hiaashuu.appteka.screen.ratings.di.RatingsComponent
import com.hiaashuu.appteka.screen.ratings.di.RatingsModule
import com.hiaashuu.appteka.screen.reviews.di.ReviewsComponent
import com.hiaashuu.appteka.screen.reviews.di.ReviewsModule
import com.hiaashuu.appteka.screen.search.di.SearchComponent
import com.hiaashuu.appteka.screen.search.di.SearchModule
import com.hiaashuu.appteka.screen.settings.di.SettingsActivityComponent
import com.hiaashuu.appteka.screen.settings.di.SettingsActivityModule
import com.hiaashuu.appteka.screen.settings.di.SettingsComponent
import com.hiaashuu.appteka.screen.settings.di.SettingsModule
import com.hiaashuu.appteka.screen.store.di.StoreComponent
import com.hiaashuu.appteka.screen.store.di.StoreModule
import com.hiaashuu.appteka.screen.users.di.SubscribersComponent
import com.hiaashuu.appteka.screen.users.di.SubscribersModule
import com.hiaashuu.appteka.screen.subscriptions.di.SubscriptionsComponent
import com.hiaashuu.appteka.screen.subscriptions.di.SubscriptionsModule
import com.hiaashuu.appteka.screen.topics.di.TopicsComponent
import com.hiaashuu.appteka.screen.topics.di.TopicsModule
import com.hiaashuu.appteka.screen.unlink.di.UnlinkComponent
import com.hiaashuu.appteka.screen.unlink.di.UnlinkModule
import com.hiaashuu.appteka.screen.unpublish.di.UnpublishComponent
import com.hiaashuu.appteka.screen.unpublish.di.UnpublishModule
import com.hiaashuu.appteka.screen.upload.di.UploadComponent
import com.hiaashuu.appteka.screen.upload.di.UploadModule
import com.hiaashuu.appteka.screen.uploads.di.UploadsComponent
import com.hiaashuu.appteka.screen.uploads.di.UploadsModule
import com.hiaashuu.appteka.upload.di.UploadServiceComponent
import com.hiaashuu.appteka.upload.di.UploadServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun moderationComponent(module: ModerationModule): ModerationComponent

    fun topicsComponent(module: TopicsModule): TopicsComponent

    fun chatComponent(module: ChatModule): ChatComponent

    fun createChatComponent(module: CreateChatModule): CreateChatComponent

    fun storeComponent(module: StoreModule): StoreComponent

    fun searchComponent(module: SearchModule): SearchComponent

    fun settingsActivityComponent(module: SettingsActivityModule): SettingsActivityComponent

    fun settingsComponent(module: SettingsModule): SettingsComponent

    fun detailsComponent(module: DetailsModule): DetailsComponent

    fun rateComponent(module: RateModule): RateComponent

    fun uploadComponent(module: UploadModule): UploadComponent

    fun downloadServiceComponent(module: DownloadServiceModule): DownloadServiceComponent

    fun uploadServiceComponent(module: UploadServiceModule): UploadServiceComponent

    fun favoriteComponent(module: FavoriteModule): FavoriteComponent

    fun requestCodeComponent(module: RequestCodeModule): RequestCodeComponent

    fun verifyCodeComponent(module: VerifyCodeModule): VerifyCodeComponent

    fun changeEmailComponent(module: ChangeEmailModule): ChangeEmailComponent

    fun editProfileComponent(module: EditProfileModule): EditProfileComponent

    fun avatarCropComponent(module: AvatarCropModule): AvatarCropComponent

    fun permissionsComponent(module: PermissionsModule): PermissionsComponent

    fun galleryComponent(module: GalleryModule): GalleryComponent

    fun profileComponent(module: ProfileModule): ProfileComponent

    fun reviewsComponent(module: ReviewsModule): ReviewsComponent

    fun homeComponent(module: HomeModule): HomeComponent

    fun ratingsComponent(module: RatingsModule): RatingsComponent

    fun agreementComponent(module: AgreementModule): AgreementComponent

    fun bduiScreenComponent(module: BduiScreenModule): BduiScreenComponent

    fun aboutComponent(module: AboutModule): AboutComponent

    fun installedComponent(module: InstalledModule): InstalledComponent

    fun distroComponent(module: DistroModule): DistroComponent

    fun subscribersComponent(module: SubscribersModule): SubscribersComponent

    fun subscriptionsComponent(module: SubscriptionsModule): SubscriptionsComponent

    fun feedComponent(module: FeedModule): FeedComponent

    fun postComponent(module: PostModule): PostComponent

    fun downloadComponent(module: DownloadsModule): DownloadsComponent

    fun unlinkComponent(module: UnlinkModule): UnlinkComponent

    fun unpublishComponent(module: UnpublishModule): UnpublishComponent

    fun uploadsComponent(module: UploadsModule): UploadsComponent

    fun analytics(): Analytics

    fun bananalytics(): Bananalytics

    fun migrationManager(): MigrationManager

    fun detailsDeepLinkParser(): DetailsDeepLinkParser

    fun profileDeepLinkParser(): ProfileDeepLinkParser

}