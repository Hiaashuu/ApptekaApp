package com.hiaashuu.appteka.screen.edit_profile

import android.net.Uri

data class EditProfileRequest(
    val nameSet: Boolean = false,
    val name: String? = null,

    val bioSet: Boolean = false,
    val bio: String? = null,

    val avatarSet: Boolean = false,

    val avatarUri: Uri? = null,
) {
    fun isEmpty(): Boolean = !nameSet && !bioSet && !avatarSet
}