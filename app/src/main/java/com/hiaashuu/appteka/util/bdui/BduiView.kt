package com.hiaashuu.appteka.util.bdui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.CompoundButton
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import android.widget.ViewFlipper
import com.google.android.material.chip.Chip
import com.google.android.material.progressindicator.BaseProgressIndicator
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.hiaashuu.appteka.util.SchedulersFactory
import com.hiaashuu.appteka.util.bdui.factory.BduiComponentFactory
import com.hiaashuu.appteka.util.bdui.factory.BduiContainerFactory
import com.hiaashuu.appteka.util.bdui.factory.BduiLayoutParamsFactory
import com.hiaashuu.appteka.util.bdui.model.BduiNode
import com.hiaashuu.appteka.util.bdui.model.action.BduiAction
import io.reactivex.rxjava3.disposables.CompositeDisposable

class BduiView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), BduiValueProvider {

    private val viewRegistry: BduiViewRegistryImpl = BduiViewRegistryImpl()
    private val hiddenStorage: BduiHiddenStorageImpl = BduiHiddenStorageImpl()
    private val disposables = CompositeDisposable()

    private var actionListener: BduiActionListener? = null
    private var schedulersFactory: SchedulersFactory? = null

    private lateinit var refResolver: BduiRefResolver
    private lateinit var transformHandler: BduiTransformHandler
    private lateinit var actionHandler: BduiActionHandler
    private lateinit var renderer: BduiRenderer

    private var isInitialized = false

    fun initialize(
        schedulersFactory: SchedulersFactory,
        preferencesStorage: BduiPreferencesStorage,
        listener: BduiActionListener
    ) {
        this.schedulersFactory = schedulersFactory
        this.actionListener = listener

        refResolver = BduiRefResolver(this)
        transformHandler = BduiTransformHandler(context, viewRegistry, hiddenStorage)
        actionHandler = BduiActionHandler(
            transformHandler = transformHandler,
            refResolver = refResolver,
            preferencesStorage = preferencesStorage,
            listener = listener,
            schedulers = schedulersFactory
        )

        transformHandler.onHiddenAction = { action ->
            val disposable = actionHandler.execute(action).subscribe({}, {})
            disposables.add(disposable)
        }

        val layoutParamsFactory = BduiLayoutParamsFactory(context)

        val componentFactory = BduiComponentFactory(
            context = context,
            layoutParamsFactory = layoutParamsFactory,
            viewRegistry = viewRegistry,
            actionHandler = actionHandler,
            nodeRenderer = { renderer }
        )

        val containerFactory = BduiContainerFactory(
            context = context,
            layoutParamsFactory = layoutParamsFactory,
            viewRegistry = viewRegistry,
            actionHandler = actionHandler,
            nodeRenderer = { renderer }
        )

        renderer = BduiRenderer(
            componentFactory = componentFactory,
            containerFactory = containerFactory,
            hiddenStorage = hiddenStorage
        )

        isInitialized = true
    }

    fun render(schema: BduiNode) {
        check(isInitialized) { "BduiView must be initialized before render(). Call initialize() first." }

        clear()

        renderer.render(schema, this)
    }

    fun clear() {
        removeAllViews()
        viewRegistry.clear()
        hiddenStorage.clear()
        disposables.clear()
    }

    fun executeAction(action: BduiAction) {
        check(isInitialized) { "BduiView must be initialized before executeAction(). Call initialize() first." }

        val disposable = actionHandler.execute(action)
            .subscribe({}, { error ->

            })
        disposables.add(disposable)
    }

    fun transform(id: String, property: String, value: Any) {
        check(isInitialized) { "BduiView must be initialized before transform(). Call initialize() first." }

        val transform = com.hiaashuu.appteka.util.bdui.model.transform.BduiPropertyTransform(
            id = id,
            property = property,
            value = value
        )
        transformHandler.apply(transform)
    }

    fun findBduiViewById(id: String): View? {
        return viewRegistry.findViewById(id)
    }

    fun getHiddenValue(id: String): Any? {
        return hiddenStorage.getHiddenValue(id)
    }

    override fun getPropertyValue(id: String, property: String): Any? {

        if (hiddenStorage.hasHidden(id)) {
            return if (property == "value") hiddenStorage.getHiddenValue(id) else null
        }

        val view = viewRegistry.findViewById(id) ?: return null

        return when (property) {
            "text" -> getTextValue(view)
            "checked" -> getCheckedValue(view)
            "enabled" -> view.isEnabled
            "visibility" -> getVisibilityValue(view)
            "value" -> getSliderValue(view)
            "rating" -> getRatingValue(view)
            "progress" -> getProgressValue(view)
            "alpha" -> view.alpha
            "error" -> getErrorValue(view)
            "displayedChild" -> getDisplayedChildValue(view)
            "flipInterval" -> getFlipIntervalValue(view)
            else -> null
        }
    }

    private fun getTextValue(view: View): String? {
        return when (view) {
            is TextInputEditText -> view.text?.toString()
            is TextView -> view.text?.toString()
            is TextInputLayout -> view.editText?.text?.toString()
            else -> null
        }
    }

    private fun getCheckedValue(view: View): Boolean? {
        return when (view) {
            is CompoundButton -> view.isChecked
            is Chip -> view.isChecked
            else -> null
        }
    }

    private fun getVisibilityValue(view: View): String {
        return when (view.visibility) {
            View.VISIBLE -> "visible"
            View.INVISIBLE -> "invisible"
            View.GONE -> "gone"
            else -> "visible"
        }
    }

    private fun getSliderValue(view: View): Float? {
        return when (view) {
            is Slider -> view.value
            else -> null
        }
    }

    private fun getRatingValue(view: View): Float? {
        return when (view) {
            is RatingBar -> view.rating
            else -> null
        }
    }

    private fun getProgressValue(view: View): Int? {
        return when (view) {
            is ProgressBar -> view.progress
            is BaseProgressIndicator<*> -> view.progress
            else -> null
        }
    }

    private fun getErrorValue(view: View): String? {
        return when (view) {
            is TextInputLayout -> view.error?.toString()
            else -> null
        }
    }

    private fun getDisplayedChildValue(view: View): Int? {
        return when (view) {
            is ViewFlipper -> view.displayedChild
            else -> null
        }
    }

    private fun getFlipIntervalValue(view: View): Int? {
        return when (view) {
            is ViewFlipper -> view.flipInterval
            else -> null
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        disposables.dispose()
    }
}

class BduiViewRegistryImpl : BduiViewRegistry {
    private val views = mutableMapOf<String, View>()

    override fun findViewById(id: String): View? = views[id]

    override fun registerView(id: String, view: View) {
        views[id] = view
    }

    override fun unregisterView(id: String) {
        views.remove(id)
    }

    override fun clear() {
        views.clear()
    }
}

class BduiHiddenStorageImpl : BduiHiddenStorage {
    private val values = mutableMapOf<String, Any?>()
    private val actions = mutableMapOf<String, com.hiaashuu.appteka.util.bdui.model.action.BduiAction?>()

    override fun hasHidden(id: String): Boolean = values.containsKey(id)

    override fun getHiddenValue(id: String): Any? = values[id]

    override fun setHiddenValue(id: String, value: Any?) {
        values[id] = value
    }

    override fun removeHidden(id: String) {
        values.remove(id)
        actions.remove(id)
    }

    override fun clear() {
        values.clear()
        actions.clear()
    }

    override fun registerHidden(id: String, value: Any?, action: com.hiaashuu.appteka.util.bdui.model.action.BduiAction?) {
        values[id] = value
        actions[id] = action
    }

    override fun getHiddenAction(id: String): com.hiaashuu.appteka.util.bdui.model.action.BduiAction? = actions[id]
}