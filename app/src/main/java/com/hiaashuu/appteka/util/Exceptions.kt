package com.hiaashuu.appteka.util

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.hiaashuu.appteka.core.permissions.Capability
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

fun Throwable.filterUnauthorizedErrors(authError: () -> Unit, other: (ex: Throwable) -> Unit) {
    if (this is HttpException && code() == 401) {
        authError()
        return
    }
    other(this)
}

fun Throwable.filterCapabilityErrors(
    authError: () -> Unit,
    capabilityDenied: (Capability) -> Unit,
    other: (ex: Throwable) -> Unit,
) {
    if (this is HttpException) {
        val code = code()
        if (code == 401) {
            authError()
            return
        }
        if (code in 400..499) {
            val capability = parseCapabilityDenial(this)
            if (capability != null) {
                capabilityDenied(capability)
                return
            }
        }
    }
    other(this)
}

private val denialGson = Gson()

private fun parseCapabilityDenial(ex: HttpException): Capability? {

    val body = try {
        ex.response()?.errorBody()?.string()
    } catch (e: Exception) {
        null
    } ?: return null
    return try {
        val parsed = denialGson.fromJson(body, CapabilityDenialBody::class.java)

        if (parsed?.reason == null && parsed?.blockedBy == null && parsed?.hintKey == null) {
            return null
        }
        Capability(
            allowed = false,
            reason = parsed.reason,
            blockedBy = parsed.blockedBy,
            hintKey = parsed.hintKey,
        )
    } catch (e: JsonSyntaxException) {
        null
    }
}

private data class CapabilityDenialBody(
    val status: Int? = null,
    val description: String? = null,
    val reason: String? = null,
    @com.google.gson.annotations.SerializedName("blocked_by")
    val blockedBy: String? = null,
    @com.google.gson.annotations.SerializedName("hint_key")
    val hintKey: String? = null,
)

fun <T : Any> Observable<T>.retryWhenNonAuthErrors(
    delay: Long = 3
): Observable<T> {
    return retryWhen { errors ->
        errors.flatMap { ex ->
            if (ex is HttpException && ex.code() in 400..499) {
                Single.create<T> { it.onError(ex) }.toObservable()
            } else {
                println("Retry after exception: " + ex.message)
                Observable.timer(delay, TimeUnit.SECONDS)
            }
        }
    }
}