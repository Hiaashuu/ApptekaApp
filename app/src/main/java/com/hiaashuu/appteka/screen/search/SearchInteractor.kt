package com.hiaashuu.appteka.screen.search

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable
import java.util.Locale

interface SearchInteractor {

    fun searchApps(query: String, offset: Int = 0): Observable<List<AppEntity>>

}

class SearchInteractorImpl(
    private val api: StoreApi,
    private val locale: Locale,
    private val schedulers: SchedulersFactory
) : SearchInteractor {

    override fun searchApps(query: String, offset: Int): Observable<List<AppEntity>> {
        return api.searchApps(
            query = query,
            offset = offset.takeIf { it > 0 },
            locale = locale.language
        )
            .map { list ->
                list.result.files
            }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}