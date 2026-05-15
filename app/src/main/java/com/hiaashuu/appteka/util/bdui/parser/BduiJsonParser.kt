package com.hiaashuu.appteka.util.bdui.parser

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.hiaashuu.appteka.util.bdui.model.BduiNode
import com.hiaashuu.appteka.util.bdui.model.action.BduiAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiCallbackAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiCopyAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiDelayAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiFocusAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiLoadAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiOpenUrlAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiReloadAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiRouteAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiRpcAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiRpcResponse
import com.hiaashuu.appteka.util.bdui.model.action.BduiScrollToAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiSequenceAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiShareAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiSnackbarAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiStoreAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiTransformAction
import com.hiaashuu.appteka.util.bdui.model.component.BduiButtonComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiCardComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiCheckboxComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiChipComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiChipGroupComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiDividerComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiFabComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiHiddenComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiIconButtonComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiIconComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiImageComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiInputComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiProgressComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiRadioComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiRadioGroupComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiRatingComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiSliderComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiSpaceComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiSwitchComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiTextComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiToolbarComponent
import com.hiaashuu.appteka.util.bdui.model.container.BduiContainer
import com.hiaashuu.appteka.util.bdui.model.container.BduiFlipperContainer
import com.hiaashuu.appteka.util.bdui.model.container.BduiFrameContainer
import com.hiaashuu.appteka.util.bdui.model.container.BduiLinearContainer
import com.hiaashuu.appteka.util.bdui.model.container.BduiRecyclerContainer
import com.hiaashuu.appteka.util.bdui.model.container.BduiScrollContainer
import com.hiaashuu.appteka.util.bdui.model.transform.BduiBatchTransform
import com.hiaashuu.appteka.util.bdui.model.transform.BduiPropertyTransform
import com.hiaashuu.appteka.util.bdui.model.transform.BduiTransform
import java.lang.reflect.Type

object BduiJsonParser {

    val gson: Gson = GsonBuilder()
        .registerTypeAdapter(BduiNode::class.java, BduiNodeDeserializer())
        .registerTypeAdapter(BduiAction::class.java, BduiActionDeserializer())
        .registerTypeAdapter(BduiTransform::class.java, BduiTransformDeserializer())
        .create()

    fun parseNode(json: String): BduiNode {
        return gson.fromJson(json, BduiNode::class.java)
    }

    fun parseRpcResponse(json: String): BduiRpcResponse {
        return gson.fromJson(json, BduiRpcResponse::class.java)
    }

    fun parseAction(json: String): BduiAction {
        return gson.fromJson(json, BduiAction::class.java)
    }
}

class BduiNodeDeserializer : JsonDeserializer<BduiNode> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): BduiNode {
        val jsonObject = json.asJsonObject
        val type = jsonObject.get("type")?.asString
            ?: throw JsonParseException("BduiNode requires 'type' field")

        val targetClass: Class<out BduiNode> = when (type) {

            BduiFrameContainer.TYPE -> BduiFrameContainer::class.java
            BduiLinearContainer.TYPE -> BduiLinearContainer::class.java
            BduiRecyclerContainer.TYPE -> BduiRecyclerContainer::class.java
            BduiScrollContainer.TYPE -> BduiScrollContainer::class.java
            BduiFlipperContainer.TYPE -> BduiFlipperContainer::class.java

            BduiHiddenComponent.TYPE -> BduiHiddenComponent::class.java
            BduiTextComponent.TYPE -> BduiTextComponent::class.java
            BduiButtonComponent.TYPE -> BduiButtonComponent::class.java
            BduiIconButtonComponent.TYPE -> BduiIconButtonComponent::class.java
            BduiFabComponent.TYPE -> BduiFabComponent::class.java
            BduiImageComponent.TYPE -> BduiImageComponent::class.java
            BduiIconComponent.TYPE -> BduiIconComponent::class.java
            BduiInputComponent.TYPE -> BduiInputComponent::class.java
            BduiSwitchComponent.TYPE -> BduiSwitchComponent::class.java
            BduiCheckboxComponent.TYPE -> BduiCheckboxComponent::class.java
            BduiRadioComponent.TYPE -> BduiRadioComponent::class.java
            BduiRadioGroupComponent.TYPE -> BduiRadioGroupComponent::class.java
            BduiChipComponent.TYPE -> BduiChipComponent::class.java
            BduiChipGroupComponent.TYPE -> BduiChipGroupComponent::class.java
            BduiProgressComponent.TYPE -> BduiProgressComponent::class.java
            BduiSliderComponent.TYPE -> BduiSliderComponent::class.java
            BduiRatingComponent.TYPE -> BduiRatingComponent::class.java
            BduiCardComponent.TYPE -> BduiCardComponent::class.java
            BduiDividerComponent.TYPE -> BduiDividerComponent::class.java
            BduiSpaceComponent.TYPE -> BduiSpaceComponent::class.java
            BduiToolbarComponent.TYPE -> BduiToolbarComponent::class.java

            else -> throw JsonParseException("Unknown BduiNode type: $type")
        }

        return context.deserialize(json, targetClass)
    }
}

class BduiActionDeserializer : JsonDeserializer<BduiAction> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): BduiAction {
        val jsonObject = json.asJsonObject
        val type = jsonObject.get("type")?.asString
            ?: throw JsonParseException("BduiAction requires 'type' field")

        val targetClass: Class<out BduiAction> = when (type) {
            BduiRpcAction.TYPE -> BduiRpcAction::class.java
            BduiCallbackAction.TYPE -> BduiCallbackAction::class.java
            BduiTransformAction.TYPE -> BduiTransformAction::class.java
            BduiSequenceAction.TYPE -> BduiSequenceAction::class.java
            BduiRouteAction.TYPE -> BduiRouteAction::class.java
            BduiOpenUrlAction.TYPE -> BduiOpenUrlAction::class.java
            BduiSnackbarAction.TYPE -> BduiSnackbarAction::class.java
            BduiCopyAction.TYPE -> BduiCopyAction::class.java
            BduiShareAction.TYPE -> BduiShareAction::class.java
            BduiDelayAction.TYPE -> BduiDelayAction::class.java
            BduiStoreAction.TYPE -> BduiStoreAction::class.java
            BduiLoadAction.TYPE -> BduiLoadAction::class.java
            BduiReloadAction.TYPE -> BduiReloadAction::class.java
            BduiFocusAction.TYPE -> BduiFocusAction::class.java
            BduiScrollToAction.TYPE -> BduiScrollToAction::class.java
            else -> throw JsonParseException("Unknown BduiAction type: $type")
        }

        return context.deserialize(json, targetClass)
    }
}

class BduiTransformDeserializer : JsonDeserializer<BduiTransform> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): BduiTransform {
        val jsonObject = json.asJsonObject
        val type = jsonObject.get("type")?.asString
            ?: throw JsonParseException("BduiTransform requires 'type' field")

        val targetClass: Class<out BduiTransform> = when (type) {
            BduiPropertyTransform.TYPE -> BduiPropertyTransform::class.java
            BduiBatchTransform.TYPE -> BduiBatchTransform::class.java
            else -> throw JsonParseException("Unknown BduiTransform type: $type")
        }

        return context.deserialize(json, targetClass)
    }
}