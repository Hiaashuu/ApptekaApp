package com.hiaashuu.appteka.screen.moderation

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable
import java.util.Locale

interface ModerationInteractor {

    fun listApps(offsetAppId: String? = null): Observable<List<AppEntity>>

}

class ModerationInteractorImpl(
    private val api: StoreApi,
    private val locale: Locale,
    private val schedulers: SchedulersFactory
) : ModerationInteractor {

    override fun listApps(offsetAppId: String?): Observable<List<AppEntity>> {
        return api
            .getModerationList(
                appId = offsetAppId,
                locale = locale.language
            )
            .map { list ->
                list.result.files
            }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}