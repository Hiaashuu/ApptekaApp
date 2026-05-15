package com.hiaashuu.appteka.util

import android.content.res.Resources
import android.util.TypedValue

fun dpToPx(px: Int, resources: Resources) = TypedValue
    .applyDimension(TypedValue.COMPLEX_UNIT_DIP, px.toFloat(), resources.displayMetrics)
    .toInt()