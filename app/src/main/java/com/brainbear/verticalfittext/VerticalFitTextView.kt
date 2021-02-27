package com.brainbear.verticalfittext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kotlin.math.abs

open class VerticalFitTextView @kotlin.jvm.JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {


    var extraPaddingTop = 0
        private set
    var extraPaddingBottom = 0
        private set


    var isFit: Boolean = true
        set(value) {
            field = value
            postInvalidate()
        }

    private var mBounds = Rect()
    private var mFontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        val prePaddingTop = extraPaddingTop
        val prePaddingBottom = extraPaddingBottom
        val rawPaddingTop = paddingTop + prePaddingTop
        val rawPaddingBottom = paddingBottom - prePaddingBottom

        if (isFit) {
            getLineTextBounds(0, mBounds)
            paint.getFontMetrics(mFontMetrics)

            val topSpace = mBounds.top + baseline - (mFontMetrics.top + baseline)
            val bottomSpace = abs(mFontMetrics.bottom + baseline - (mBounds.bottom + baseline))

            val padding = (topSpace - bottomSpace).toInt()


            extraPaddingTop = padding / 2
            extraPaddingBottom = padding / 2
            if (extraPaddingTop != prePaddingTop || extraPaddingBottom != prePaddingBottom) {
                setPadding(
                    paddingLeft,
                    rawPaddingTop - extraPaddingTop,
                    paddingRight,
                    rawPaddingBottom + extraPaddingBottom
                )
            }
        } else {
            extraPaddingTop = 0
            extraPaddingBottom = 0

            if (rawPaddingTop != paddingTop || rawPaddingBottom != paddingBottom) {
                setPadding(rawPaddingTop, paddingLeft, paddingRight, rawPaddingBottom)
            }
        }

    }


    private fun getLineTextBounds(line: Int, bounds: Rect) {
        val start = layout.getLineStart(line)
        val end = layout.getLineEnd(line)
        paint.getTextBounds(text.toString(), start, end, bounds)
    }
}