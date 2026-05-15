package com.hiaashuu.appteka.screen.topics

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.topics.api.PinTopicResponse
import com.hiaashuu.appteka.screen.topics.api.TopicsResponse
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable
import java.util.Locale

interface TopicsInteractor {

    fun listTopics(offset: Int = 0): Observable<TopicsResponse>

    fun pinTopic(topicId: Int): Observable<PinTopicResponse>

}

class TopicsInteractorImpl(
    private val api: StoreApi,
    private val locale: Locale,
    private val schedulers: SchedulersFactory
) : TopicsInteractor {

    override fun listTopics(offset: Int): Observable<TopicsResponse> {
        return api.getTopicsList(offset = offset, locale = locale.language)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun pinTopic(topicId: Int): Observable<PinTopicResponse> {
        return api.pinTopic(topicId = topicId)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}