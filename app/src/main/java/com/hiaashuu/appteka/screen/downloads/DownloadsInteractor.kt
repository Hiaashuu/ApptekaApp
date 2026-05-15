package com.hiaashuu.appteka.screen.downloads

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.screen.downloads.api.DeleteDownloadedResponse
import com.hiaashuu.appteka.user.api.UserBrief
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable
import java.util.Locale

data class UserBriefWrapper(
    val userBrief: UserBrief?
)

interface DownloadsInteractor {

    fun listApps(offsetAppId: String? = null): Observable<List<AppEntity>>

    fun deleteDownloaded(appId: String): Observable<DeleteDownloadedResponse>

    fun getUserBrief(): Observable<UserBriefWrapper>

}

class DownloadsInteractorImpl(
    private val api: StoreApi,
    private val userId: Int,
    private val locale: Locale,
    private val schedulers: SchedulersFactory
) : DownloadsInteractor {

    override fun listApps(offsetAppId: String?): Observable<List<AppEntity>> {
        return api
            .getDownloadsList(
                userId = userId,
                appId = offsetAppId,
                locale = locale.language
            )
            .map { list ->
                list.result.files
            }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun deleteDownloaded(appId: String): Observable<DeleteDownloadedResponse> {
        return api
            .deleteDownloaded(appId)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun getUserBrief(): Observable<UserBriefWrapper> {
        return api
            .getUserBrief(userId = null)
            .map { UserBriefWrapper(it.result) }
            .onErrorReturn { UserBriefWrapper(userBrief = null) }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}