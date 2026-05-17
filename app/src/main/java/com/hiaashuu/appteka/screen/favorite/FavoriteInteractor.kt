package com.hiaashuu.appteka.screen.favorite

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.util.Locale

interface FavoriteInteractor {

    fun listApps(offsetAppId: String? = null): Observable<List<AppEntity>>

    fun markFavorite(appId: String, isFavorite: Boolean): Completable

}

class FavoriteInteractorImpl(
    private val api: StoreApi,
    private val userId: Int,
    private val locale: Locale,
    private val schedulers: SchedulersFactory
) : FavoriteInteractor {

    override fun listApps(offsetAppId: String?): Observable<List<AppEntity>> {
        return api
            .getFavoriteList(
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

    override fun markFavorite(appId: String, isFavorite: Boolean): Completable {
        return api
            .markFavorite(appId = appId, isFavorite = if (isFavorite) 1 else 0)
            .ignoreElement()
            .subscribeOn(schedulers.io())
    }

}