package com.hiaashuu.appteka.util.bdui.model

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel

@GsonModel
data class BduiTextStyle(
    @SerializedName("textSize")
    val textSize: Int? = null,
    @SerializedName("textColor")
    val textColor: String? = null,
    @SerializedName("fontWeight")
    val fontWeight: String? = null,
    @SerializedName("fontFamily")
    val fontFamily: String? = null,
    @SerializedName("textAlign")
    val textAlign: String? = null,
    @SerializedName("maxLines")
    val maxLines: Int? = null,
    @SerializedName("ellipsize")
    val ellipsize: String? = null,
    @SerializedName("lineHeight")
    val lineHeight: Int? = null,
    @SerializedName("letterSpacing")
    val letterSpacing: Float? = null,
    @SerializedName("textAllCaps")
    val textAllCaps: Boolean? = null,
    @SerializedName("includeFontPadding")
    val includeFontPadding: Boolean? = null
)

@GsonModel
data class BduiBackgroundStyle(
    @SerializedName("color")
    val color: String? = null,
    @SerializedName("cornerRadius")
    val cornerRadius: Int? = null,
    @SerializedName("cornerRadiusTopStart")
    val cornerRadiusTopStart: Int? = null,
    @SerializedName("cornerRadiusTopEnd")
    val cornerRadiusTopEnd: Int? = null,
    @SerializedName("cornerRadiusBottomStart")
    val cornerRadiusBottomStart: Int? = null,
    @SerializedName("cornerRadiusBottomEnd")
    val cornerRadiusBottomEnd: Int? = null,
    @SerializedName("strokeColor")
    val strokeColor: String? = null,
    @SerializedName("strokeWidth")
    val strokeWidth: Int? = null
)

@GsonModel
data class BduiImageStyle(
    @SerializedName("scaleType")
    val scaleType: String? = null,
    @SerializedName("tint")
    val tint: String? = null,
    @SerializedName("cornerRadius")
    val cornerRadius: Int? = null
)