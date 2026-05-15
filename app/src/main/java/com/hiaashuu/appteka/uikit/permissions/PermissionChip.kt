package com.hiaashuu.appteka.uikit.permissions

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.isVisible
import com.google.android.material.chip.Chip
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.core.permissions.Capability
import com.hiaashuu.appteka.core.permissions.CapabilityHintResolver

class PermissionChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.chipStyle,
) : Chip(context, attrs, defStyleAttr) {

    private val hintResolver = CapabilityHintResolver(resources)

    init {
        chipIcon = context.getDrawable(R.drawable.ic_lock)
        isChipIconVisible = true
        isClickable = false
        isFocusable = false
        isVisible = false
    }

    fun showFor(capability: Capability?) {
        if (capability == null || capability.allowed) {
            isVisible = false
            return
        }
        text = hintResolver.resolveText(capability)
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }
}