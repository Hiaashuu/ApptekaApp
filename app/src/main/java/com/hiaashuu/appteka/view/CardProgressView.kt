package com.hiaashuu.appteka.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import com.hiaashuu.appteka.R

class CardProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = context.resources.displayMetrics.density * 3f
        color = ContextCompat.getColor(context, R.color.primary_color)
        strokeCap = Paint.Cap.ROUND
    }

    private val path = Path()
    private val drawPath = Path()
    private val pathMeasure = PathMeasure()
    private val rect = RectF()

    var progress: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    var cornerRadius: Float = context.resources.displayMetrics.density * 20f
        set(value) {
            field = value
            updatePath()
            invalidate()
        }

    var isIndeterminate: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                if (value) {
                    startAnimator()
                } else {
                    stopAnimator()
                }
                invalidate()
            }
        }

    private var indeterminateOffset = 0f
    private var animator: ValueAnimator? = null

    private fun startAnimator() {
        if (animator == null) {
            animator = ValueAnimator.ofFloat(0f, 1f).apply {
                duration = 1500
                repeatCount = ValueAnimator.INFINITE
                interpolator = LinearInterpolator()
                addUpdateListener {
                    indeterminateOffset = it.animatedValue as Float
                    invalidate()
                }
            }
        }
        animator?.start()
    }

    private fun stopAnimator() {
        animator?.cancel()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updatePath()
    }

    private fun updatePath() {
        rect.set(
            paint.strokeWidth / 2,
            paint.strokeWidth / 2,
            width - paint.strokeWidth / 2,
            height - paint.strokeWidth / 2
        )
        path.reset()
        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW)
        pathMeasure.setPath(path, false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isIndeterminate) {
            val length = pathMeasure.length
            val start = indeterminateOffset * length
            val stop = start + length * 0.25f // 25% of the perimeter
            
            drawPath.reset()
            if (stop > length) {
                pathMeasure.getSegment(start, length, drawPath, true)
                pathMeasure.getSegment(0f, stop - length, drawPath, true)
            } else {
                pathMeasure.getSegment(start, stop, drawPath, true)
            }
            canvas.drawPath(drawPath, paint)
        } else if (progress > 0) {
            val length = pathMeasure.length
            val stop = length * (progress / 100f)
            
            drawPath.reset()
            pathMeasure.getSegment(0f, stop, drawPath, true)
            canvas.drawPath(drawPath, paint)
        }
    }
    
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAnimator()
    }
}