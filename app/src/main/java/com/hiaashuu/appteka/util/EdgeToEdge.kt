package com.hiaashuu.appteka.util

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding

fun View.applyTopInsets() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updatePadding(top = insets.top)
        windowInsets
    }
}

fun View.applyBottomInsets() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updatePadding(bottom = insets.bottom)
        windowInsets
    }
}

fun View.applyBottomInsetsAsMargin() {
    val initialMargin = (layoutParams as? ViewGroup.MarginLayoutParams)?.bottomMargin ?: 0
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            bottomMargin = initialMargin + insets.bottom
        }
        windowInsets
    }
}

fun View.applyVerticalInsets() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updatePadding(top = insets.top, bottom = insets.bottom)
        windowInsets
    }
}

fun View.applySystemBarInsets() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updatePadding(
            left = insets.left,
            top = insets.top,
            right = insets.right,
            bottom = insets.bottom
        )
        windowInsets
    }
}

fun View.applyBottomMarginForView(anchorView: View) {

    anchorView.addOnLayoutChangeListener { _, _, _, _, bottom, _, _, _, oldBottom ->
        if (bottom != oldBottom) {
            this.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = anchorView.height
            }
        }
    }

    anchorView.post {
        this.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            bottomMargin = anchorView.height
        }
    }
}