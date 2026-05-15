package com.hiaashuu.appteka.screen.edit_profile

import com.hiaashuu.appteka.core.StoreApi
import com.hiaashuu.appteka.screen.profile.api.ProfileResponse
import com.hiaashuu.appteka.util.ImageCompressor
import com.hiaashuu.appteka.util.SchedulersFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

interface EditProfileInteractor {

    fun loadProfile(): Observable<ProfileResponse>

    fun updateProfile(request: EditProfileRequest): Observable<ProfileResponse>

}

class EditProfileInteractorImpl(
    private val api: StoreApi,
    private val compressor: ImageCompressor,
    private val schedulers: SchedulersFactory,
) : EditProfileInteractor {

    override fun loadProfile(): Observable<ProfileResponse> {
        return api
            .getProfile(userId = null)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    override fun updateProfile(request: EditProfileRequest): Observable<ProfileResponse> {

        val name = if (request.nameSet) (request.name.orEmpty()).toTextPart() else null
        val bio = if (request.bioSet) (request.bio.orEmpty()).toTextPart() else null
        val avatar: MultipartBody.Part? = when {
            !request.avatarSet -> null
            request.avatarUri != null -> MultipartBody.Part.createFormData(
                "avatar",
                "avatar.jpg",
                compressor.asRequestBody(request.avatarUri),
            )

            else -> MultipartBody.Part.createFormData(
                "avatar",
                "",
                EMPTY_BYTES.toRequestBody(IMAGE_JPEG),
            )
        }

        return api
            .updateProfile(name, bio, avatar)
            .map { it.result }
            .toObservable()
            .subscribeOn(schedulers.io())
    }

    private fun String.toTextPart(): RequestBody = toRequestBody(TEXT_PLAIN)

    private companion object {
        val TEXT_PLAIN = "text/plain".toMediaType()
        val IMAGE_JPEG = "image/jpeg".toMediaType()
        val EMPTY_BYTES = ByteArray(0)
    }
}