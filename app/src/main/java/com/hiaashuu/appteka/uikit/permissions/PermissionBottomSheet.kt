package com.hiaashuu.appteka.uikit.permissions

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.core.permissions.Capability
import com.hiaashuu.appteka.core.permissions.CapabilityHintResolver

object PermissionBottomSheet {

    fun show(
        context: Context,
        capability: Capability?,
        title: CharSequence = context.getString(R.string.permission_banner_read_only_title),
    ) {
        if (capability == null || capability.allowed) return

        val dialog = BottomSheetDialog(context)
        val view = LayoutInflater.from(context)
            .inflate(R.layout.bottom_sheet_permission, null, false)

        view.findViewById<TextView>(R.id.permission_sheet_title).text = title
        view.findViewById<TextView>(R.id.permission_sheet_body).text =
            CapabilityHintResolver(context.resources).resolveText(capability)
        view.findViewById<MaterialButton>(R.id.permission_sheet_dismiss).setOnClickListener {
            dialog.dismiss()
        }

        dialog.setContentView(view)
        dialog.show()
    }
}