package com.hiaashuu.appteka.upload

import android.os.Parcelable
import com.hiaashuu.appteka.categories.CategoryItem
import com.hiaashuu.appteka.screen.upload.api.CheckExistResponse
import com.hiaashuu.appteka.screen.upload.dto.UploadScreenshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class UploadInfo(
    val checkExist: CheckExistResponse,
    val screenshots: List<UploadScreenshot>,
    val category: CategoryItem,
    val description: String,
    val whatsNew: String,
    val exclusive: Boolean,
    val openSource: Boolean,
    val sourceUrl: String?
) : Parcelable