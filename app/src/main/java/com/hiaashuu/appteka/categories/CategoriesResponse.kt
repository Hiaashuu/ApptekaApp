package com.hiaashuu.appteka.categories

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import kotlinx.parcelize.Parcelize

@Parcelize
@GsonModel
data class CategoriesResponse(
    @SerializedName("categories")
    val categories: List<Category>
) : Parcelable