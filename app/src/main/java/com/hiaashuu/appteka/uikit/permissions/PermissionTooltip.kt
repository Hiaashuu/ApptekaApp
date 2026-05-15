package com.hiaashuu.appteka.uikit.permissions

import android.view.View
import androidx.appcompat.widget.TooltipCompat
import com.hiaashuu.appteka.core.permissions.Capability
import com.hiaashuu.appteka.core.permissions.CapabilityHintResolver

object PermissionTooltip {

    fun attach(view: View, capability: Capability?) {
        if (capability == null || capability.allowed) {
            TooltipCompat.setTooltipText(view, null)
            return
        }
        val resolver = CapabilityHintResolver(view.resources)
        TooltipCompat.setTooltipText(view, resolver.resolveText(capability))
    }
}