package com.hiaashuu.appteka.screen.post.adapter.submit

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubmitItem(
    override val id: Long,
) : Item, Parcelable