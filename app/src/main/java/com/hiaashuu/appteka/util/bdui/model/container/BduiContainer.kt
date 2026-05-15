package com.hiaashuu.appteka.util.bdui.model.container

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import com.hiaashuu.appteka.util.bdui.model.BduiLayoutParams
import com.hiaashuu.appteka.util.bdui.model.BduiNode
import com.hiaashuu.appteka.util.bdui.model.action.BduiAction

sealed interface BduiContainer : BduiNode {
    val children: List<BduiNode>?
}

@GsonModel
data class BduiFrameContainer(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("children")
    override val children: List<BduiNode>? = null
) : BduiContainer {
    companion object {
        const val TYPE = "frame"
    }
}

@GsonModel
data class BduiLinearContainer(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("children")
    override val children: List<BduiNode>? = null,
    @SerializedName("orientation")
    val orientation: String = "vertical",
    @SerializedName("gravity")
    val gravity: String? = null,
    @SerializedName("weightSum")
    val weightSum: Float? = null,
    @SerializedName("divider")
    val divider: BduiDividerConfig? = null
) : BduiContainer {
    companion object {
        const val TYPE = "linear"
    }
}

@GsonModel
data class BduiDividerConfig(
    @SerializedName("showDividers")
    val showDividers: String? = null,
    @SerializedName("dividerPadding")
    val dividerPadding: Int? = null
)

@GsonModel
data class BduiRecyclerContainer(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("children")
    override val children: List<BduiNode>? = null,
    @SerializedName("orientation")
    val orientation: String = "vertical",
    @SerializedName("spanCount")
    val spanCount: Int = 1,
    @SerializedName("reverseLayout")
    val reverseLayout: Boolean = false,
    @SerializedName("itemSpacing")
    val itemSpacing: Int? = null
) : BduiContainer {
    companion object {
        const val TYPE = "recycler"
    }
}

@GsonModel
data class BduiScrollContainer(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("children")
    override val children: List<BduiNode>? = null,
    @SerializedName("orientation")
    val orientation: String = "vertical",
    @SerializedName("fillViewport")
    val fillViewport: Boolean = false
) : BduiContainer {
    companion object {
        const val TYPE = "scroll"
    }
}

@GsonModel
data class BduiFlipperContainer(
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("layoutParams")
    override val layoutParams: BduiLayoutParams? = null,
    @SerializedName("action")
    override val action: BduiAction? = null,
    @SerializedName("children")
    override val children: List<BduiNode>? = null,
    @SerializedName("displayedChild")
    val displayedChild: Int = 0,
    @SerializedName("autoStart")
    val autoStart: Boolean = false,
    @SerializedName("flipInterval")
    val flipInterval: Int = 3000,
    @SerializedName("inAnimation")
    val inAnimation: String? = null,
    @SerializedName("outAnimation")
    val outAnimation: String? = null
) : BduiContainer {
    companion object {
        const val TYPE = "flipper"
    }
}