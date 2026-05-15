package com.hiaashuu.appteka.screen.post

import android.annotation.SuppressLint
import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.post.api.FeedConfigResponse
import com.hiaashuu.appteka.screen.post.api.FeedPostResponse
import com.hiaashuu.appteka.screen.post.dto.PostImage
import com.hiaashuu.appteka.upload.UploadScreenshotsResponse
import com.hiaashuu.appteka.util.ImageCompressor
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody

interface PostInteractor {

    fun uploadImages(imgList: List<PostImage>): Observable<UploadScreenshotsResponse>

    fun post(text: String, scrIds: List<String>, reactionIds: List<String>): Observable<FeedPostResponse>

    fun config(): Observable<FeedConfigResponse>

}

class PostInteractorImpl(
    private val api: StoreApi,
    private val compressor: ImageCompressor,
    private val schedulers: SchedulersFactory
) : PostInteractor {

    @SuppressLint("DefaultLocale")
    override fun uploadImages(imgList: List<PostImage>): Observable<UploadScreenshotsResponse> {
        return api
            .uploadScreenshots(
                images = imgList.map { img ->
                    val uri = img.original
                    val name = String.format(format = "img%d.jpg", uri.hashCode())
                    MultipartBody.Part.createFormData(
                        "images",
                        name,
                        compressor.asRequestBody(uri)
                    )
                }
            )
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun post(text: String, scrIds: List<String>, reactionIds: List<String>): Observable<FeedPostResponse> {
        return api
            .postFeed(
                text = text,
                scrIds = scrIds,
                reactionIds = reactionIds,
            )
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun config(): Observable<FeedConfigResponse> {
        return api.feedConfig()
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

}