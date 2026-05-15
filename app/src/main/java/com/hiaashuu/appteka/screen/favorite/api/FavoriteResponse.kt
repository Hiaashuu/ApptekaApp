package com.hiaashuu.appteka.screen.favorite.api

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.dto.AppEntity
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
class FavoriteResponse(
    @SerializedName("entries")
    val files: List<AppEntity>
)