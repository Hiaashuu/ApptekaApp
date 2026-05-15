package com.hiaashuu.appteka.util.bdui

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import android.widget.ViewFlipper
import com.google.android.material.chip.Chip
import com.google.android.material.progressindicator.BaseProgressIndicator
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputLayout
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.util.bdui.model.transform.BduiBatchTransform
import com.hiaashuu.appteka.util.bdui.model.transform.BduiPropertyTransform
import com.hiaashuu.appteka.util.bdui.model.transform.BduiTransform

class BduiTransformHandler(
    private val context: Context,
    private val viewRegistry: BduiViewRegistry,
    private val hiddenStorage: BduiHiddenStorage
) {

    var onHiddenAction: ((com.hiaashuu.appteka.util.bdui.model.action.BduiAction) -> Unit)? = null

    fun apply(transform: BduiTransform) {
        when (transform) {
            is BduiPropertyTransform -> applyPropertyTransform(transform)
            is BduiBatchTransform -> applyBatchTransform(transform)
        }
    }

    private fun applyPropertyTransform(transform: BduiPropertyTransform) {
        val id = transform.id
        val property = transform.property
        val value = transform.value

        if (hiddenStorage.hasHidden(id)) {
            if (property == "value") {
                hiddenStorage.setHiddenValue(id, value)
            }

            hiddenStorage.getHiddenAction(id)?.let { action ->
                onHiddenAction?.invoke(action)
            }
            return
        }

        val view = viewRegistry.findViewById(id) ?: return
        applyPropertyToView(view, property, value)
    }

    private fun applyBatchTransform(transform: BduiBatchTransform) {
        transform.transforms.forEach { apply(it) }
    }

    private fun applyPropertyToView(view: View, property: String, value: Any) {
        when (property) {
            "text" -> applyText(view, value)
            "visibility" -> applyVisibility(view, value)
            "enabled" -> applyEnabled(view, value)
            "alpha" -> applyAlpha(view, value)
            "checked" -> applyChecked(view, value)
            "error" -> applyError(view, value)
            "progress" -> applyProgress(view, value)
            "rating" -> applyRating(view, value)
            "value" -> applyValue(view, value)
            "src" -> applySrc(view, value)
            "tint" -> applyTint(view, value)
            "hint" -> applyHint(view, value)
            "helperText" -> applyHelperText(view, value)
            "displayedChild" -> applyDisplayedChild(view, value)
            "autoStart" -> applyAutoStart(view, value)
            "flipInterval" -> applyFlipInterval(view, value)
            "inAnimation" -> applyInAnimation(view, value)
            "outAnimation" -> applyOutAnimation(view, value)
        }
    }

    private fun applyText(view: View, value: Any) {
        when (view) {
            is TextView -> view.text = value.toString()
        }
    }

    private fun applyVisibility(view: View, value: Any) {
        view.visibility = when (value.toString().lowercase()) {
            "visible" -> View.VISIBLE
            "invisible" -> View.INVISIBLE
            "gone" -> View.GONE
            else -> View.VISIBLE
        }
    }

    private fun applyEnabled(view: View, value: Any) {
        view.isEnabled = when (value) {
            is Boolean -> value
            is String -> value.toBoolean()
            else -> true
        }
    }

    private fun applyAlpha(view: View, value: Any) {
        view.alpha = when (value) {
            is Number -> value.toFloat()
            is String -> value.toFloatOrNull() ?: 1f
            else -> 1f
        }
    }

    private fun applyChecked(view: View, value: Any) {
        val checked = when (value) {
            is Boolean -> value
            is String -> value.toBoolean()
            else -> false
        }
        when (view) {
            is CompoundButton -> view.isChecked = checked
            is Chip -> view.isChecked = checked
        }
    }

    private fun applyError(view: View, value: Any) {
        when (view) {
            is TextInputLayout -> {
                val errorText = value.toString()
                view.error = if (errorText.isEmpty()) null else errorText
            }
        }
    }

    private fun applyProgress(view: View, value: Any) {
        val progress = when (value) {
            is Number -> value.toInt()
            is String -> value.toIntOrNull() ?: 0
            else -> 0
        }
        when (view) {
            is ProgressBar -> view.progress = progress
            is BaseProgressIndicator<*> -> view.progress = progress
        }
    }

    private fun applyRating(view: View, value: Any) {
        val rating = when (value) {
            is Number -> value.toFloat()
            is String -> value.toFloatOrNull() ?: 0f
            else -> 0f
        }
        when (view) {
            is RatingBar -> view.rating = rating
        }
    }

    private fun applyValue(view: View, value: Any) {
        val floatValue = when (value) {
            is Number -> value.toFloat()
            is String -> value.toFloatOrNull() ?: 0f
            else -> 0f
        }
        when (view) {
            is Slider -> view.value = floatValue.coerceIn(view.valueFrom, view.valueTo)
        }
    }

    private fun applySrc(view: View, value: Any) {

        when (view) {
            is ImageView -> {

            }
        }
    }

    private fun applyTint(view: View, value: Any) {
        val colorString = value.toString()
        try {
            val color = Color.parseColor(colorString)
            when (view) {
                is ImageView -> view.setColorFilter(color)
            }
        } catch (e: IllegalArgumentException) {

        }
    }

    private fun applyHint(view: View, value: Any) {
        when (view) {
            is TextInputLayout -> view.hint = value.toString()
            is TextView -> view.hint = value.toString()
        }
    }

    private fun applyHelperText(view: View, value: Any) {
        when (view) {
            is TextInputLayout -> view.helperText = value.toString()
        }
    }

    private fun applyDisplayedChild(view: View, value: Any) {
        val index = when (value) {
            is Number -> value.toInt()
            is String -> value.toIntOrNull() ?: 0
            else -> 0
        }
        when (view) {
            is ViewFlipper -> {
                if (index >= 0 && index < view.childCount) {
                    view.displayedChild = index
                }
            }
        }
    }

    private fun applyAutoStart(view: View, value: Any) {
        val autoStart = when (value) {
            is Boolean -> value
            is String -> value.toBoolean()
            else -> false
        }
        when (view) {
            is ViewFlipper -> {
                if (autoStart) {
                    view.startFlipping()
                } else {
                    view.stopFlipping()
                }
            }
        }
    }

    private fun applyFlipInterval(view: View, value: Any) {
        val interval = when (value) {
            is Number -> value.toInt()
            is String -> value.toIntOrNull() ?: 3000
            else -> 3000
        }
        when (view) {
            is ViewFlipper -> view.flipInterval = interval
        }
    }

    private fun applyInAnimation(view: View, value: Any) {
        when (view) {
            is ViewFlipper -> {
                getAnimation(value.toString())?.let { anim ->
                    view.inAnimation = anim
                }
            }
        }
    }

    private fun applyOutAnimation(view: View, value: Any) {
        when (view) {
            is ViewFlipper -> {
                getAnimation(value.toString())?.let { anim ->
                    view.outAnimation = anim
                }
            }
        }
    }

    private fun getAnimation(name: String): android.view.animation.Animation? {
        val animRes = when (name.lowercase()) {
            "fade_in" -> android.R.anim.fade_in
            "fade_out" -> android.R.anim.fade_out
            "slide_in_left" -> android.R.anim.slide_in_left
            "slide_out_right" -> android.R.anim.slide_out_right
            "slide_in_right" -> R.anim.slide_in_right
            "slide_out_left" -> R.anim.slide_out_left
            else -> return null
        }
        return AnimationUtils.loadAnimation(context, animRes)
    }
}

interface BduiViewRegistry {
    fun findViewById(id: String): View?
    fun registerView(id: String, view: View)
    fun unregisterView(id: String)
    fun clear()
}

interface BduiHiddenStorage {
    fun hasHidden(id: String): Boolean
    fun getHiddenValue(id: String): Any?
    fun setHiddenValue(id: String, value: Any?)
    fun removeHidden(id: String)
    fun clear()

    fun registerHidden(id: String, value: Any?, action: com.hiaashuu.appteka.util.bdui.model.action.BduiAction?)
    fun getHiddenAction(id: String): com.hiaashuu.appteka.util.bdui.model.action.BduiAction?
}