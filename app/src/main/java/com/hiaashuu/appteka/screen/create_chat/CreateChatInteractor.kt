package com.hiaashuu.appteka.screen.create_chat

import android.net.Uri
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.dto.TopicEntity
import com.hiaashuu.appteka.util.ImageCompressor
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

interface CreateChatInteractor {

    fun createTopic(title: String, description: String, avatar: Uri): Observable<TopicEntity>

}

class CreateChatInteractorImpl(
    private val api: StoreApi,
    private val compressor: ImageCompressor,
    private val schedulers: SchedulersFactory,
) : CreateChatInteractor {

    override fun createTopic(
        title: String,
        description: String,
        avatar: Uri,
    ): Observable<TopicEntity> {
        val avatarPart = MultipartBody.Part.createFormData(
            AVATAR_PART_NAME,
            "avatar_${avatar.hashCode()}.jpg",
            compressor.asRequestBody(avatar),
        )
        return api
            .createTopic(
                packageName = null,
                title = title.toPlainPart(),
                description = description.toPlainPart(),
                avatar = avatarPart,
            )
            .map { it.result.topic }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    private fun String.toPlainPart(): RequestBody = toRequestBody(TEXT_PLAIN)

}

private const val AVATAR_PART_NAME = "avatar"
private val TEXT_PLAIN = "text/plain".toMediaTypeOrNull()