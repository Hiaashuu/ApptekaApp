package com.hiaashuu.appteka.util.bdui.model.action

import com.google.gson.annotations.SerializedName
import com.hiaashuu.appteka.util.GsonModel
import com.hiaashuu.appteka.util.bdui.model.transform.BduiTransform

sealed interface BduiAction {
    val type: String
}

@GsonModel
data class BduiRpcAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("endpoint")
    val endpoint: String,
    @SerializedName("method")
    val method: String = "POST",
    @SerializedName("payload")
    val payload: Any? = null
) : BduiAction {
    companion object {
        const val TYPE = "rpc"
    }
}

@GsonModel
data class BduiCallbackAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("name")
    val name: String,
    @SerializedName("data")
    val data: Any? = null
) : BduiAction {
    companion object {
        const val TYPE = "callback"
    }
}

@GsonModel
data class BduiTransformAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("transform")
    val transform: BduiTransform
) : BduiAction {
    companion object {
        const val TYPE = "transform"
    }
}

@GsonModel
data class BduiSequenceAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("actions")
    val actions: List<BduiAction>
) : BduiAction {
    companion object {
        const val TYPE = "sequence"
    }
}

@GsonModel
data class BduiRouteAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("screen")
    val screen: String,
    @SerializedName("params")
    val params: Map<String, Any>? = null
) : BduiAction {
    companion object {
        const val TYPE = "route"
    }
}

@GsonModel
data class BduiOpenUrlAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("url")
    val url: String,
    @SerializedName("external")
    val external: Boolean = true
) : BduiAction {
    companion object {
        const val TYPE = "open_url"
    }
}

@GsonModel
data class BduiSnackbarAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("message")
    val message: String,
    @SerializedName("duration")
    val duration: String = "short",
    @SerializedName("actionText")
    val actionText: String? = null,
    @SerializedName("action")
    val action: BduiAction? = null
) : BduiAction {
    companion object {
        const val TYPE = "snackbar"
    }
}

@GsonModel
data class BduiCopyAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("text")
    val text: String,
    @SerializedName("label")
    val label: String = "Copied text"
) : BduiAction {
    companion object {
        const val TYPE = "copy"
    }
}

@GsonModel
data class BduiShareAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("text")
    val text: String,
    @SerializedName("title")
    val title: String? = null
) : BduiAction {
    companion object {
        const val TYPE = "share"
    }
}

@GsonModel
data class BduiDelayAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("duration")
    val duration: Long
) : BduiAction {
    companion object {
        const val TYPE = "delay"
    }
}

@GsonModel
data class BduiStoreAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("key")
    val key: String,
    @SerializedName("value")
    val value: Any?
) : BduiAction {
    companion object {
        const val TYPE = "store"
    }
}

@GsonModel
data class BduiLoadAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("key")
    val key: String,
    @SerializedName("targetId")
    val targetId: String,
    @SerializedName("defaultValue")
    val defaultValue: Any? = null
) : BduiAction {
    companion object {
        const val TYPE = "load"
    }
}

@GsonModel
data class BduiReloadAction(
    @SerializedName("type")
    override val type: String = TYPE
) : BduiAction {
    companion object {
        const val TYPE = "reload"
    }
}

@GsonModel
data class BduiFocusAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("id")
    val id: String,
    @SerializedName("showKeyboard")
    val showKeyboard: Boolean = true
) : BduiAction {
    companion object {
        const val TYPE = "focus"
    }
}

@GsonModel
data class BduiScrollToAction(
    @SerializedName("type")
    override val type: String = TYPE,
    @SerializedName("id")
    val id: String,
    @SerializedName("smooth")
    val smooth: Boolean = true
) : BduiAction {
    companion object {
        const val TYPE = "scroll_to"
    }
}

@GsonModel
data class BduiRpcResponse(
    @SerializedName("action")
    val action: BduiAction
)