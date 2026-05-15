package com.hiaashuu.appteka.screen.upload.adapter.category

import android.os.Parcelable
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.categories.CategoryItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectCategoryItem(
    override val id: Long,
    val category: CategoryItem?,
    val errorRequiredField: Boolean,
) : Item, Parcelable