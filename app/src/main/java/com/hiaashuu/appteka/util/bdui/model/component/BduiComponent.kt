package com.hiaashuu.appteka.util.bdui.model.component

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import com.hiaashuu.appteka.util.bdui.model.BduiBackgroundStyle
import com.hiaashuu.appteka.util.bdui.model.BduiImageStyle
import com.hiaashuu.appteka.util.bdui.model.BduiLayoutParams
import com.hiaashuu.appteka.util.bdui.model.BduiNode
import com.hiaashuu.appteka.util.bdui.model.action.BduiAction

sealed interface BduiComponent : BduiNode

@GsonModel
data class BduiHiddenComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("value")
    val value: Any? = null
) : BduiComponent {
    companion object {
        const val TYPE = "hidden"
    }
}

@GsonModel
data class BduiTextComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("textSize")
    val textSize: Int? = null,
    @SerializedName("textColor")
    val textColor: String? = null,
    @SerializedName("textStyle")
    val textStyle: String? = null,
    @SerializedName("gravity")
    val gravity: String? = null,
    @SerializedName("maxLines")
    val maxLines: Int? = null,
    @SerializedName("lineHeight")
    val lineHeight: Int? = null,
    @SerializedName("letterSpacing")
    val letterSpacing: Float? = null,
    @SerializedName("selectable")
    val selectable: Boolean = false,
    @SerializedName("autoLink")
    val autoLink: Boolean = false
) : BduiComponent {
    companion object {
        const val TYPE = "text"
    }
}

@GsonModel
data class BduiButtonComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("variant")
    val variant: String = "primary",
    @SerializedName("icon")
    val icon: String? = null,
    @SerializedName("iconGravity")
    val iconGravity: String? = null,
    @SerializedName("enabled")
    val enabled: Boolean? = null
) : BduiComponent {
    companion object {
        const val TYPE = "button"
    }
}

@GsonModel
data class BduiIconButtonComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("variant")
    val variant: String = "standard",
    @SerializedName("contentDescription")
    val contentDescription: String? = null,
    @SerializedName("enabled")
    val enabled: Boolean? = null
) : BduiComponent {
    companion object {
        const val TYPE = "icon_button"
    }
}

@GsonModel
data class BduiFabComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("size")
    val size: String = "normal",
    @SerializedName("extended")
    val extended: Boolean = false,
    @SerializedName("contentDescription")
    val contentDescription: String? = null
) : BduiComponent {
    companion object {
        const val TYPE = "fab"
    }
}

@GsonModel
data class BduiImageComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("src")
    val src: String? = null,
    @SerializedName("placeholder")
    val placeholder: String? = null,
    @SerializedName("imageStyle")
    val imageStyle: BduiImageStyle? = null,
    @SerializedName("contentDescription")
    val contentDescription: String? = null
) : BduiComponent {
    companion object {
        const val TYPE = "image"
    }
}

@GsonModel
data class BduiIconComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("size")
    val size: Int = 24,
    @SerializedName("tint")
    val tint: String? = null,
    @SerializedName("contentDescription")
    val contentDescription: String? = null
) : BduiComponent {
    companion object {
        const val TYPE = "icon"
    }
}

@GsonModel
data class BduiInputComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("hint")
    val hint: String? = null,
    @SerializedName("helperText")
    val helperText: String? = null,
    @SerializedName("error")
    val error: String? = null,
    @SerializedName("variant")
    val variant: String = "outlined",
    @SerializedName("inputType")
    val inputType: String = "text",
    @SerializedName("maxLines")
    val maxLines: Int = 1,
    @SerializedName("maxLength")
    val maxLength: Int? = null,
    @SerializedName("startIcon")
    val startIcon: String? = null,
    @SerializedName("endIcon")
    val endIcon: String? = null,
    @SerializedName("enabled")
    val enabled: Boolean? = null
) : BduiComponent {
    companion object {
        const val TYPE = "input"
    }
}

@GsonModel
data class BduiSwitchComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("checked")
    val checked: Boolean? = null,
    @SerializedName("enabled")
    val enabled: Boolean? = null
) : BduiComponent {
    companion object {
        const val TYPE = "switch"
    }
}

@GsonModel
data class BduiCheckboxComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("checked")
    val checked: Boolean? = null,
    @SerializedName("enabled")
    val enabled: Boolean? = null
) : BduiComponent {
    companion object {
        const val TYPE = "checkbox"
    }
}

@GsonModel
data class BduiRadioComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("checked")
    val checked: Boolean? = null,
    @SerializedName("enabled")
    val enabled: Boolean? = null
) : BduiComponent {
    companion object {
        const val TYPE = "radio"
    }
}

@GsonModel
data class BduiRadioGroupComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("orientation")
    val orientation: String = "vertical",
    @SerializedName("items")
    val items: List<BduiRadioItem>? = null,
    @SerializedName("selectedId")
    val selectedId: String? = null
) : BduiComponent {
    companion object {
        const val TYPE = "radio_group"
    }
}

@GsonModel
data class BduiRadioItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("text")
    val text: String
)

@GsonModel
data class BduiChipComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("text")
    val text: String,
    @SerializedName("variant")
    val variant: String = "assist",
    @SerializedName("icon")
    val icon: String? = null,
    @SerializedName("checked")
    val checked: Boolean? = null,
    @SerializedName("checkable")
    val checkable: Boolean? = null,
    @SerializedName("closeIcon")
    val closeIcon: Boolean? = null,
    @SerializedName("enabled")
    val enabled: Boolean? = null
) : BduiComponent {
    companion object {
        const val TYPE = "chip"
    }
}

@GsonModel
data class BduiChipGroupComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("chips")
    val chips: List<BduiChipItem>? = null,
    @SerializedName("singleSelection")
    val singleSelection: Boolean = false,
    @SerializedName("singleLine")
    val singleLine: Boolean = false
) : BduiComponent {
    companion object {
        const val TYPE = "chip_group"
    }
}

@GsonModel
data class BduiChipItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("checked")
    val checked: Boolean? = null,
    @SerializedName("icon")
    val icon: String? = null
)

@GsonModel
data class BduiProgressComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("variant")
    val variant: String = "circular",
    @SerializedName("indeterminate")
    val indeterminate: Boolean = true,
    @SerializedName("progress")
    val progress: Int = 0,
    @SerializedName("max")
    val max: Int = 100
) : BduiComponent {
    companion object {
        const val TYPE = "progress"
    }
}

@GsonModel
data class BduiSliderComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("value")
    val value: Float = 0f,
    @SerializedName("valueFrom")
    val valueFrom: Float = 0f,
    @SerializedName("valueTo")
    val valueTo: Float = 100f,
    @SerializedName("stepSize")
    val stepSize: Float? = null,
    @SerializedName("enabled")
    val enabled: Boolean? = null
) : BduiComponent {
    companion object {
        const val TYPE = "slider"
    }
}

@GsonModel
data class BduiRatingComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("rating")
    val rating: Float = 0f,
    @SerializedName("numStars")
    val numStars: Int = 5,
    @SerializedName("stepSize")
    val stepSize: Float = 1f,
    @SerializedName("isIndicator")
    val isIndicator: Boolean = false
) : BduiComponent {
    companion object {
        const val TYPE = "rating"
    }
}

@GsonModel
data class BduiCardComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("children")
    val children: List<BduiNode>? = null,
    @SerializedName("variant")
    val variant: String = "elevated",
    @SerializedName("cornerRadius")
    val cornerRadius: Int = 16,
    @SerializedName("elevation")
    val elevation: Int = 1
) : BduiComponent {
    companion object {
        const val TYPE = "card"
    }
}

@GsonModel
data class BduiDividerComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("orientation")
    val orientation: String = "horizontal",
    @SerializedName("insetStart")
    val insetStart: Int = 0,
    @SerializedName("insetEnd")
    val insetEnd: Int = 0,
    @SerializedName("thickness")
    val thickness: Int = 1
) : BduiComponent {
    companion object {
        const val TYPE = "divider"
    }
}

@GsonModel
data class BduiSpaceComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null
) : BduiComponent {
    companion object {
        const val TYPE = "space"
    }
}

@GsonModel
data class BduiToolbarComponent(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("subtitle")
    val subtitle: String? = null,
    @SerializedName("navigationIcon")
    val navigationIcon: String? = null,
    @SerializedName("navigationAction")
    val navigationAction: BduiAction? = null,
    @SerializedName("menu")
    val menu: List<BduiMenuItem>? = null,
    @SerializedName("titleCentered")
    val titleCentered: Boolean = false,
    @SerializedName("subtitleCentered")
    val subtitleCentered: Boolean = false,
    @SerializedName("elevation")
    val elevation: Int = 0,
    @SerializedName("backgroundColor")
    val backgroundColor: String? = null,
    @SerializedName("titleTextColor")
    val titleTextColor: String? = null,
    @SerializedName("subtitleTextColor")
    val subtitleTextColor: String? = null,
    @SerializedName("navigationIconTint")
    val navigationIconTint: String? = null,
    @SerializedName("logo")
    val logo: String? = null,
    @SerializedName("collapseIcon")
    val collapseIcon: String? = null,
    @SerializedName("contentInsetStart")
    val contentInsetStart: Int? = null,
    @SerializedName("contentInsetEnd")
    val contentInsetEnd: Int? = null
) : BduiComponent {
    companion object {
        const val TYPE = "toolbar"
    }
}

@GsonModel
data class BduiMenuItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("icon")
    val icon: String? = null,
    @SerializedName("showAsAction")
    val showAsAction: String? = null,
    @SerializedName("enabled")
    val enabled: Boolean? = null,
    @SerializedName("visible")
    val visible: Boolean? = null,
    @SerializedName("action")
    val action: BduiAction? = null,
    @SerializedName("iconTint")
    val iconTint: String? = null
)