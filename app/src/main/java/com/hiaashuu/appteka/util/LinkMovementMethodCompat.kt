package com.hiaashuu.appteka.util

import android.text.Selection
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.widget.TextView

object LinkMovementMethodCompat : LinkMovementMethod() {

    override fun onTouchEvent(widget: TextView, buffer: Spannable, event: MotionEvent): Boolean {
        val action = event.action

        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
            var x = event.x.toInt()
            var y = event.y.toInt()

            x -= widget.totalPaddingLeft
            y -= widget.totalPaddingTop

            x += widget.scrollX
            y += widget.scrollY

            val layout = widget.layout
            val line = layout.getLineForVertical(y)
            val off = layout.getOffsetForHorizontal(line, x.toFloat())

            val links = buffer.getSpans(off, off, ClickableSpan::class.java)

            if (links.isNotEmpty()) {
                val link = links[0]
                if (action == MotionEvent.ACTION_UP) {
                    link.onClick(widget)
                } else {
                    Selection.setSelection(
                        buffer,
                        buffer.getSpanStart(link),
                        buffer.getSpanEnd(link)
                    )
                }
                return true
            } else {
                Selection.removeSelection(buffer)
            }
        }

        return false
    }

}