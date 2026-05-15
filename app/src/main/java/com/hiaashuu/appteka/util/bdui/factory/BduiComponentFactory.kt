package com.hiaashuu.appteka.util.bdui.factory

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.Space
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.util.bdui.BduiActionHandler
import com.hiaashuu.appteka.util.bdui.BduiViewRegistry
import com.hiaashuu.appteka.util.bdui.model.BduiLayoutParams
import com.hiaashuu.appteka.util.bdui.model.BduiNode
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
import com.google.android.material.appbar.MaterialToolbar
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.MenuCompat
import com.caverock.androidsvg.SVG
import com.hiaashuu.appteka.util.svgToDrawable
import com.tomclaw.imageloader.util.fetch

class BduiComponentFactory(
    private val context: Context,
    private val layoutParamsFactory: BduiLayoutParamsFactory,
    private val viewRegistry: BduiViewRegistry,
    private val actionHandler: BduiActionHandler,
    private val nodeRenderer: () -> BduiNodeRenderer
) {

    fun create(component: BduiComponent, parent: ViewGroup): View? {
        val view = when (component) {
            is BduiHiddenComponent -> null
            is BduiTextComponent -> createText(component)
            is BduiButtonComponent -> createButton(component)
            is BduiIconButtonComponent -> createIconButton(component)
            is BduiFabComponent -> createFab(component)
            is BduiImageComponent -> createImage(component)
            is BduiIconComponent -> createIcon(component)
            is BduiInputComponent -> createInput(component)
            is BduiSwitchComponent -> createSwitch(component)
            is BduiCheckboxComponent -> createCheckbox(component)
            is BduiRadioComponent -> createRadio(component)
            is BduiRadioGroupComponent -> createRadioGroup(component)
            is BduiChipComponent -> createChip(component)
            is BduiChipGroupComponent -> createChipGroup(component)
            is BduiProgressComponent -> createProgress(component)
            is BduiSliderComponent -> createSlider(component)
            is BduiRatingComponent -> createRating(component)
            is BduiCardComponent -> createCard(component, parent)
            is BduiDividerComponent -> createDivider(component)
            is BduiSpaceComponent -> createSpace(component)
            is BduiToolbarComponent -> createToolbar(component)
        }

        view?.let {

            val effectiveLayoutParams = if (component is BduiToolbarComponent && component.layoutParams == null) {
                BduiLayoutParams(width = "match_parent", height = "wrap_content")
            } else {
                component.layoutParams
            }
            applyLayoutParams(it, effectiveLayoutParams, parent)
            setupAction(it, component)
            viewRegistry.registerView(component.id, it)
        }

        return view
    }

    private fun createText(component: BduiTextComponent): View {
        return TextView(context).apply {
            text = component.text

            component.textSize?.let { setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, it.toFloat()) }
            component.textColor?.let { setTextColor(parseColor(it)) }
            component.textStyle?.let { style ->
                val typeStyle = when (style.lowercase()) {
                    "bold" -> android.graphics.Typeface.BOLD
                    "italic" -> android.graphics.Typeface.ITALIC
                    "bold_italic", "bold|italic" -> android.graphics.Typeface.BOLD_ITALIC
                    else -> android.graphics.Typeface.NORMAL
                }
                setTypeface(typeface, typeStyle)
            }
            component.gravity?.let { gravity = parseGravity(it) }
            component.maxLines?.let {
                maxLines = it
                ellipsize = android.text.TextUtils.TruncateAt.END
            }
            component.lineHeight?.let {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                    lineHeight = (it * context.resources.displayMetrics.scaledDensity).toInt()
                }
            }
            component.letterSpacing?.let { letterSpacing = it }

            isClickable = component.selectable
            setTextIsSelectable(component.selectable)
            if (component.autoLink) {
                autoLinkMask = android.text.util.Linkify.ALL
            }
        }
    }

    private fun createButton(component: BduiButtonComponent): View {
        return MaterialButton(context, null, getButtonStyleAttr(component.variant ?: "primary")).apply {
            text = component.text
            isEnabled = component.enabled ?: true
            component.icon?.let { iconName ->
                setIconResource(getDrawableResource(iconName))
            }
            iconGravity = when (component.iconGravity ?: "start") {
                "end" -> MaterialButton.ICON_GRAVITY_END
                "top" -> MaterialButton.ICON_GRAVITY_TOP
                "textStart" -> MaterialButton.ICON_GRAVITY_TEXT_START
                "textEnd" -> MaterialButton.ICON_GRAVITY_TEXT_END
                else -> MaterialButton.ICON_GRAVITY_START
            }
        }
    }

    private fun createIconButton(component: BduiIconButtonComponent): View {
        val styleAttr = when (component.variant ?: "standard") {
            "filled" -> com.google.android.material.R.attr.materialIconButtonFilledStyle
            "tonal" -> com.google.android.material.R.attr.materialIconButtonFilledTonalStyle
            "outlined" -> com.google.android.material.R.attr.materialIconButtonOutlinedStyle
            else -> com.google.android.material.R.attr.materialIconButtonStyle
        }
        return MaterialButton(context, null, styleAttr).apply {
            component.icon?.let { setIconResource(getDrawableResource(it)) }
            isEnabled = component.enabled ?: true
            contentDescription = component.contentDescription
        }
    }

    private fun createFab(component: BduiFabComponent): View {
        return if (component.extended == true && component.text != null) {
            ExtendedFloatingActionButton(context).apply {
                component.icon?.let { setIconResource(getDrawableResource(it)) }
                text = component.text
                contentDescription = component.contentDescription
            }
        } else {
            FloatingActionButton(context).apply {
                component.icon?.let { setImageResource(getDrawableResource(it)) }
                size = when (component.size ?: "normal") {
                    "mini" -> FloatingActionButton.SIZE_MINI
                    "large" -> FloatingActionButton.SIZE_NORMAL
                    else -> FloatingActionButton.SIZE_NORMAL
                }
                contentDescription = component.contentDescription
            }
        }
    }

    private fun createImage(component: BduiImageComponent): View {
        return ImageView(context).apply {
            val scaleTypeValue = when (component.imageStyle?.scaleType) {
                "centerCrop" -> ImageView.ScaleType.CENTER_CROP
                "fitCenter" -> ImageView.ScaleType.FIT_CENTER
                "centerInside" -> ImageView.ScaleType.CENTER_INSIDE
                "fitXY" -> ImageView.ScaleType.FIT_XY
                "center" -> ImageView.ScaleType.CENTER
                else -> ImageView.ScaleType.FIT_CENTER
            }
            scaleType = scaleTypeValue
            contentDescription = component.contentDescription

            component.src?.let { src ->
                when {

                    src.startsWith("res:") -> {
                        setImageResource(getDrawableResource(src.removePrefix("res:")))
                    }

                    src.trimStart().startsWith("<svg") || src.trimStart().startsWith("<?xml") -> {
                        try {
                            val drawable = svgToDrawable(src, context.resources)
                            setImageDrawable(drawable)
                        } catch (e: Exception) {

                            component.placeholder?.let { placeholder ->
                                setImageResource(getDrawableResource(placeholder.removePrefix("res:")))
                            }
                        }
                    }

                    src.startsWith("http://") || src.startsWith("https://") -> {
                        val placeholderRes = component.placeholder?.let { placeholder ->
                            getDrawableResource(placeholder.removePrefix("res:"))
                        } ?: 0

                        fetch(src) {
                            if (scaleTypeValue == ImageView.ScaleType.CENTER_CROP) {
                                centerCrop()
                            }
                            if (placeholderRes != 0) {
                                placeholder(placeholderRes)
                            }
                        }
                    }

                    else -> {
                        val resId = getDrawableResource(src)
                        if (resId != 0) {
                            setImageResource(resId)
                        }
                    }
                }
            }
        }
    }

    private fun createIcon(component: BduiIconComponent): View {
        return ImageView(context).apply {
            component.icon?.let { setImageResource(getDrawableResource(it)) }
            val sizePx = dpToPx(component.size ?: 24)
            layoutParams = ViewGroup.LayoutParams(sizePx, sizePx)
            component.tint?.let { tint ->
                try {
                    setColorFilter(Color.parseColor(tint))
                } catch (e: IllegalArgumentException) {

                }
            }
            contentDescription = component.contentDescription
        }
    }

    private fun createInput(component: BduiInputComponent): View {
        val isFilled = (component.variant ?: "outlined") == "filled"
        val styleRes = if (isFilled) {
            com.google.android.material.R.style.Widget_Material3_TextInputLayout_FilledBox
        } else {
            R.style.MaterialTextInputExpressive
        }
        val themedContext = android.view.ContextThemeWrapper(context, styleRes)

        val textInputLayout = TextInputLayout(themedContext).apply {
            hint = component.hint
            helperText = component.helperText
            error = component.error
            isEnabled = component.enabled ?: true
            component.startIcon?.let { setStartIconDrawable(getDrawableResource(it)) }
            component.endIcon?.let { setEndIconDrawable(getDrawableResource(it)) }
            component.maxLength?.let { counterMaxLength = it; isCounterEnabled = true }
        }

        val editText = TextInputEditText(themedContext).apply {
            id = View.generateViewId()
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setText(component.text)
            inputType = getInputType(component.inputType ?: "text", component.maxLines ?: 1)
            maxLines = component.maxLines ?: 1
        }
        textInputLayout.addView(editText)

        return textInputLayout
    }

    private fun createSwitch(component: BduiSwitchComponent): View {
        return MaterialSwitch(context).apply {
            text = component.text
            isChecked = component.checked ?: false
            isEnabled = component.enabled ?: true
        }
    }

    private fun createCheckbox(component: BduiCheckboxComponent): View {
        return CheckBox(context).apply {
            text = component.text
            isChecked = component.checked ?: false
            isEnabled = component.enabled ?: true
        }
    }

    private fun createRadio(component: BduiRadioComponent): View {
        return RadioButton(context).apply {
            text = component.text
            isChecked = component.checked ?: false
            isEnabled = component.enabled ?: true
        }
    }

    private fun createRadioGroup(component: BduiRadioGroupComponent): View {
        return RadioGroup(context).apply {
            orientation = if ((component.orientation ?: "vertical") == "horizontal") {
                RadioGroup.HORIZONTAL
            } else {
                RadioGroup.VERTICAL
            }
            component.items?.forEach { item ->
                val radioButton = RadioButton(context).apply {
                    id = View.generateViewId()
                    text = item.text
                    tag = item.id
                    isChecked = item.id == component.selectedId
                }
                addView(radioButton)
                viewRegistry.registerView(item.id, radioButton)
            }
        }
    }

    private fun createChip(component: BduiChipComponent): View {
        return Chip(context).apply {
            text = component.text
            isChecked = component.checked ?: false
            isCheckable = component.checkable ?: false
            isCloseIconVisible = component.closeIcon ?: false
            isEnabled = component.enabled ?: true
            component.icon?.let { setChipIconResource(getDrawableResource(it)) }
        }
    }

    private fun createChipGroup(component: BduiChipGroupComponent): View {
        return ChipGroup(context).apply {
            isSingleSelection = component.singleSelection
            isSingleLine = component.singleLine
            component.chips?.forEach { chipItem ->
                val chip = Chip(context).apply {
                    id = View.generateViewId()
                    text = chipItem.text
                    tag = chipItem.id
                    isChecked = chipItem.checked ?: false
                    isCheckable = true
                    chipItem.icon?.let { setChipIconResource(getDrawableResource(it)) }
                }
                addView(chip)
                viewRegistry.registerView(chipItem.id, chip)
            }
        }
    }

    private fun createProgress(component: BduiProgressComponent): View {
        val indeterminate = component.indeterminate ?: true
        return when (component.variant ?: "circular") {
            "linear" -> LinearProgressIndicator(context).apply {
                isIndeterminate = indeterminate
                if (!indeterminate) {
                    max = component.max ?: 100
                    progress = component.progress ?: 0
                }
            }
            else -> CircularProgressIndicator(context).apply {
                isIndeterminate = indeterminate
                if (!indeterminate) {
                    max = component.max ?: 100
                    progress = component.progress ?: 0
                }
            }
        }
    }

    private fun createSlider(component: BduiSliderComponent): View {
        return Slider(context).apply {
            valueFrom = component.valueFrom ?: 0f
            valueTo = component.valueTo ?: 100f
            value = (component.value ?: 0f).coerceIn(valueFrom, valueTo)
            val step = component.stepSize ?: 0f
            if (step > 0) {
                stepSize = step
            }
            isEnabled = component.enabled ?: true
        }
    }

    private fun createRating(component: BduiRatingComponent): View {
        return RatingBar(context).apply {
            numStars = component.numStars ?: 5
            rating = component.rating ?: 0f
            stepSize = component.stepSize ?: 1f
            setIsIndicator(component.isIndicator ?: false)
        }
    }

    private fun createCard(component: BduiCardComponent, parent: ViewGroup): View {
        return MaterialCardView(context).apply {
            radius = dpToPx(component.cornerRadius ?: 16).toFloat()
            cardElevation = dpToPx(component.elevation ?: 1).toFloat()

            val contentLayout = FrameLayout(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
            }
            addView(contentLayout)

            component.children?.forEach { child ->
                nodeRenderer().render(child, contentLayout)
            }
        }
    }

    private fun createDivider(component: BduiDividerComponent): View {
        return MaterialDivider(context).apply {
            dividerInsetStart = dpToPx(component.insetStart ?: 0)
            dividerInsetEnd = dpToPx(component.insetEnd ?: 0)
            dividerThickness = dpToPx(component.thickness ?: 1)
        }
    }

    private fun createSpace(component: BduiSpaceComponent): View {
        return Space(context)
    }

    private fun createToolbar(component: BduiToolbarComponent): View {
        return MaterialToolbar(context).apply {

            val actionBarTypedArray = context.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
            minimumHeight = actionBarTypedArray.getDimensionPixelSize(0, 0)
            actionBarTypedArray.recycle()

            val typedArray = context.obtainStyledAttributes(intArrayOf(R.attr.toolbar_background))
            val toolbarBackground = typedArray.getColor(0, Color.TRANSPARENT)
            typedArray.recycle()
            setBackgroundColor(toolbarBackground)

            title = component.title
            subtitle = component.subtitle
            isTitleCentered = component.titleCentered ?: false
            isSubtitleCentered = component.subtitleCentered ?: false

            component.navigationIcon?.let { iconName ->

                if (iconName == "ic_arrow_back" || iconName == "back" || iconName == "home") {
                    val navTypedArray = context.obtainStyledAttributes(intArrayOf(android.R.attr.homeAsUpIndicator))
                    val homeAsUpIndicator = navTypedArray.getDrawable(0)
                    navTypedArray.recycle()
                    navigationIcon = homeAsUpIndicator
                } else {
                    val iconRes = getDrawableResource(iconName)
                    if (iconRes != 0) {
                        setNavigationIcon(iconRes)
                    }
                }
            }

            component.navigationIconTint?.let { tint ->
                try {
                    setNavigationIconTint(Color.parseColor(tint))
                } catch (e: IllegalArgumentException) {

                }
            } ?: run {

                if (component.navigationIcon != null &&
                    component.navigationIcon != "ic_arrow_back" &&
                    component.navigationIcon != "back" &&
                    component.navigationIcon != "home") {
                    val tintTypedArray = context.obtainStyledAttributes(intArrayOf(android.R.attr.textColorPrimary))
                    val defaultTint = tintTypedArray.getColor(0, Color.BLACK)
                    tintTypedArray.recycle()
                    setNavigationIconTint(defaultTint)
                }
            }

            component.navigationAction?.let { action ->
                setNavigationOnClickListener {
                    actionHandler.execute(action)
                        .subscribe({}, { error ->

                        })
                }
            }

            component.backgroundColor?.let { bgColor ->
                try {
                    setBackgroundColor(Color.parseColor(bgColor))
                } catch (e: IllegalArgumentException) {

                }
            }

            component.titleTextColor?.let { color ->
                try {
                    setTitleTextColor(Color.parseColor(color))
                } catch (e: IllegalArgumentException) {

                }
            }

            component.subtitleTextColor?.let { color ->
                try {
                    setSubtitleTextColor(Color.parseColor(color))
                } catch (e: IllegalArgumentException) {

                }
            }

            component.logo?.let { logoName ->
                val logoRes = getDrawableResource(logoName)
                if (logoRes != 0) {
                    setLogo(logoRes)
                }
            }

            component.contentInsetStart?.let { contentInsetStartWithNavigation = dpToPx(it) }
            component.contentInsetEnd?.let { contentInsetEndWithActions = dpToPx(it) }

            component.menu?.let { menuItems ->
                menuItems.forEachIndexed { index, menuItem ->
                    val item = menu.add(Menu.NONE, index, index, menuItem.title)

                    menuItem.icon?.let { iconName ->
                        val iconRes = getDrawableResource(iconName)
                        if (iconRes != 0) {
                            item.setIcon(iconRes)
                        }
                    }

                    menuItem.iconTint?.let { tint ->
                        try {
                            item.iconTintList = android.content.res.ColorStateList.valueOf(
                                Color.parseColor(tint)
                            )
                        } catch (e: IllegalArgumentException) {

                        }
                    }

                    item.setShowAsAction(
                        when (menuItem.showAsAction) {
                            "always" -> MenuItem.SHOW_AS_ACTION_ALWAYS
                            "never" -> MenuItem.SHOW_AS_ACTION_NEVER
                            "withText" -> MenuItem.SHOW_AS_ACTION_WITH_TEXT
                            "collapseActionView" -> MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
                            else -> MenuItem.SHOW_AS_ACTION_IF_ROOM
                        }
                    )

                    item.isEnabled = menuItem.enabled ?: true
                    item.isVisible = menuItem.visible ?: true

                    viewRegistry.registerView(menuItem.id, this)
                }

                setOnMenuItemClickListener { item ->
                    val menuItem = menuItems.getOrNull(item.itemId)
                    menuItem?.action?.let { action ->
                        actionHandler.execute(action)
                            .subscribe({}, { error ->

                            })
                        true
                    } ?: false
                }
            }
        }
    }

    private fun applyLayoutParams(view: View, params: BduiLayoutParams?, parent: ViewGroup) {
        view.layoutParams = layoutParamsFactory.create(params, parent)
        params?.let {
            it.visibility?.let { vis ->
                view.visibility = when (vis.lowercase()) {
                    "visible" -> View.VISIBLE
                    "invisible" -> View.INVISIBLE
                    "gone" -> View.GONE
                    else -> View.VISIBLE
                }
            }
            it.alpha?.let { alpha -> view.alpha = alpha }
            it.enabled?.let { enabled -> view.isEnabled = enabled }
            it.clickable?.let { clickable -> view.isClickable = clickable }
            it.padding?.let { padding ->
                view.setPadding(
                    dpToPx(padding.getLeft()),
                    dpToPx(padding.getTop()),
                    dpToPx(padding.getRight()),
                    dpToPx(padding.getBottom())
                )
            }
        }
    }

    private fun setupAction(view: View, component: BduiComponent) {
        component.action?.let { action ->
            view.setOnClickListener {
                actionHandler.execute(action)
                    .subscribe({}, { error ->

                    })
            }
        }
    }

    private fun getButtonStyleAttr(variant: String): Int {
        return when (variant) {
            "secondary" -> com.google.android.material.R.attr.materialButtonOutlinedStyle
            "outlined" -> com.google.android.material.R.attr.materialButtonOutlinedStyle
            "elevated" -> com.google.android.material.R.attr.materialButtonElevatedStyle
            "text" -> android.R.attr.borderlessButtonStyle
            else -> com.google.android.material.R.attr.materialButtonStyle
        }
    }

    private fun getInputType(type: String, maxLines: Int): Int {
        val multiline = if (maxLines > 1) InputType.TYPE_TEXT_FLAG_MULTI_LINE else 0
        return when (type) {
            "number" -> InputType.TYPE_CLASS_NUMBER
            "email" -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            "password" -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            "phone" -> InputType.TYPE_CLASS_PHONE
            "multiline" -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
            else -> InputType.TYPE_CLASS_TEXT or multiline
        }
    }

    private fun getDrawableResource(name: String): Int {
        return context.resources.getIdentifier(name, "drawable", context.packageName)
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }

    private fun parseColor(color: String): Int {
        return try {
            Color.parseColor(color)
        } catch (e: IllegalArgumentException) {
            Color.BLACK
        }
    }

    private fun parseGravity(gravity: String): Int {
        var result = 0
        gravity.split("|").forEach { part ->
            result = result or when (part.trim().lowercase()) {
                "center" -> android.view.Gravity.CENTER
                "center_horizontal" -> android.view.Gravity.CENTER_HORIZONTAL
                "center_vertical" -> android.view.Gravity.CENTER_VERTICAL
                "start", "left" -> android.view.Gravity.START
                "end", "right" -> android.view.Gravity.END
                "top" -> android.view.Gravity.TOP
                "bottom" -> android.view.Gravity.BOTTOM
                "fill" -> android.view.Gravity.FILL
                "fill_horizontal" -> android.view.Gravity.FILL_HORIZONTAL
                "fill_vertical" -> android.view.Gravity.FILL_VERTICAL
                else -> 0
            }
        }
        return if (result == 0) android.view.Gravity.START else result
    }
}

interface BduiNodeRenderer {
    fun render(node: BduiNode, parent: ViewGroup): View?
}