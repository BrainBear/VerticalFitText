package com.brainbear.verticalfittext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet

class MetricsTextView @kotlin.jvm.JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : VerticalFitTextView(context, attrs, defStyleAttr) {


    private val baselineColor = Color.MAGENTA
    private val topLineColor = Color.BLUE
    private val bottomLineColor = Color.GREEN
    private val ascentLineColor = Color.RED
    private val descentLineColor = Color.YELLOW
    private val boundsLineColor = Color.CYAN

    private val mTextBounds = Rect()
    private var mFontMetrics = Paint.FontMetricsInt()
    private var linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (drawBaseline) {
            linePaint.color = baselineColor
            drawHorizontalLine(baseline, canvas, linePaint)
        }

        if (drawTopLine || drawBottomLine || drawAscentLine || drawDescentLine) {
            paint.getFontMetricsInt(mFontMetrics)

            if (drawTopLine) {
                linePaint.color = topLineColor
                drawHorizontalLine(baseline + mFontMetrics.top, canvas, linePaint)
            }

            if (drawBottomLine) {
                linePaint.color = bottomLineColor
                drawHorizontalLine(baseline + mFontMetrics.bottom, canvas, linePaint)
            }

            if (drawAscentLine) {
                linePaint.color = ascentLineColor
                drawHorizontalLine(baseline + mFontMetrics.ascent, canvas, linePaint)
            }

            if (drawDescentLine) {
                linePaint.color = descentLineColor
                drawHorizontalLine(baseline + mFontMetrics.descent, canvas, linePaint)
            }
        }

        if (drawTextBounds) {
            paint.getTextBounds(text.toString(), 0, text.length, mTextBounds)
            linePaint.color = boundsLineColor
            canvas.save()
            canvas.translate(0f, baseline.toFloat())
            canvas.drawRect(mTextBounds, linePaint)
            canvas.restore()
        }

    }


    private fun drawHorizontalLine(y: Int, canvas: Canvas, paint: Paint) {
        canvas.drawLine(0.toFloat(), y.toFloat(), width.toFloat(), y.toFloat(), paint)
    }


    var drawBaseline: Boolean = true
        set(value) {
            field = value
            postInvalidate()
        }

    var drawTopLine: Boolean = true
        set(value) {
            field = value
            postInvalidate()
        }

    var drawBottomLine: Boolean = true
        set(value) {
            field = value
            postInvalidate()
        }

    var drawAscentLine: Boolean = true
        set(value) {
            field = value
            postInvalidate()
        }

    var drawDescentLine: Boolean = true
        set(value) {
            field = value
            postInvalidate()
        }

    var drawTextBounds: Boolean = true
        set(value) {
            field = value
            postInvalidate()
        }

}