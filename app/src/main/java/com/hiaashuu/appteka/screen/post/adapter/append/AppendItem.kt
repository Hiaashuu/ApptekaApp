package com.hiaashuu.appteka.screen.post.adapter.append

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppendItem(
    override val id: Long,
) : Item, Parcelable