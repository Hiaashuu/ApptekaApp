package com.hiaashuu.appteka.screen.users.adapter

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.user.api.UserBrief

interface UserItem : Item, Parcelable {
    val user: UserBrief
    var hasMore: Boolean
    var hasProgress: Boolean
}