package com.hiaashuu.appteka.core

import com.hiaashuu.appteka.dto.StoreResponse
import com.hiaashuu.appteka.screen.home.api.StatusResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StandByApi {

    @GET("status.php")
    fun getStatus(
        @Query("locale") locale: String,
        @Query("build") build: Long,
    ): Single<StoreResponse<StatusResponse>>

}