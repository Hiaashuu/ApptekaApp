package com.hiaashuu.appteka.uikit.permissions

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.core.permissions.Capability
import com.hiaashuu.appteka.core.permissions.CapabilityHintResolver

class PermissionBanner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val icon: ImageView
    private val title: TextView
    private val body: TextView
    private val action: MaterialButton
    private val hintResolver = CapabilityHintResolver(resources)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_permission_banner, this, true)
        icon = findViewById(R.id.permission_banner_icon)
        title = findViewById(R.id.permission_banner_title)
        body = findViewById(R.id.permission_banner_body)
        action = findViewById(R.id.permission_banner_action)
        isVisible = false
    }

    fun showFor(
        capability: Capability?,
        titleText: CharSequence = resources.getString(R.string.permission_banner_read_only_title),
        onAction: (() -> Unit)? = null,
    ) {
        if (capability == null || capability.allowed) {
            isVisible = false
            return
        }
        title.text = titleText
        body.text = hintResolver.resolveText(capability)
        if (onAction != null) {
            action.isVisible = true
            action.setOnClickListener { onAction() }
        } else {
            action.isVisible = false
            action.setOnClickListener(null)
        }
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }

    fun setIcon(resId: Int) {
        icon.setImageResource(resId)
    }
}