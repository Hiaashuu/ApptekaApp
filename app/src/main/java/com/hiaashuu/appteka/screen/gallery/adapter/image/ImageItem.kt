package com.hiaashuu.appteka.screen.gallery.adapter.image

import android.net.Uri
import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageItem(
    override val id: Long,
    val uri: Uri,
): Parcelable, Item