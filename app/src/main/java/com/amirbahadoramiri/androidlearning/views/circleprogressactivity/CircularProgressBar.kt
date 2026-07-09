package com.amirbahadoramiri.androidlearning.views.circleprogressactivity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import com.amirbahadoramiri.androidlearning.R

class CircularProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var progress = 0
    private var max = 100

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        typeface = Typeface.DEFAULT_BOLD
    }

    private val rectF = RectF()

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularProgressBar)

        progress = typedArray.getInt(R.styleable.CircularProgressBar_progress, 0)
        max = typedArray.getInt(R.styleable.CircularProgressBar_max, 100)

        // رنگ پیش‌فرض
        backgroundPaint.color = typedArray.getColor(
            R.styleable.CircularProgressBar_backgroundColor, 0xFFE0E0E0.toInt()
        )
        progressPaint.color = getColorForProgress(progress)
        textPaint.color = progressPaint.color   // متن هم هم‌رنگ پراگرس

        backgroundPaint.strokeWidth = typedArray.getDimension(
            R.styleable.CircularProgressBar_strokeWidth, 20f
        )
        progressPaint.strokeWidth = backgroundPaint.strokeWidth

        textPaint.textSize = typedArray.getDimension(
            R.styleable.CircularProgressBar_textSize, 48f
        )

        typedArray.recycle()
    }

    private fun getColorForProgress(prog: Int): Int {
        val percent = prog.toFloat() / max   // 0.0f تا 1.0f

        return when {
            percent == 0f -> 0xFFFF4747.toInt()     // قرمز

            percent <= 0.25f -> {  // 0% تا 25%
                interpolateColor(0xFFFF4747.toInt(), 0xFFFF8C00.toInt(), percent / 0.25f)
            }

            percent <= 0.50f -> {  // 25% تا 50% → زرد لیمویی
                interpolateColor(0xFFFF8C00.toInt(), 0xFFFFEA00.toInt(), (percent - 0.25f) / 0.25f)
            }

            percent <= 0.75f -> {  // 50% تا 75%
                interpolateColor(0xFFFFEA00.toInt(), 0xFFFFCC00.toInt(), (percent - 0.50f) / 0.25f)  // زرد طلایی ملایم
            }

            else -> {              // 75% تا 100% → سبز پررنگ‌تر
                interpolateColor(0xFFFFCC00.toInt(), 0xFF00FF85.toInt(), (percent - 0.75f) / 0.25f)
            }
        }
    }

    // تابع کمکی برای محاسبه رنگ بین دو رنگ (Linear Interpolation)
    private fun interpolateColor(color1: Int, color2: Int, factor: Float): Int {
        val f = factor.coerceIn(0f, 1f)

        val a1 = (color1 shr 24) and 0xFF
        val r1 = (color1 shr 16) and 0xFF
        val g1 = (color1 shr 8) and 0xFF
        val b1 = color1 and 0xFF

        val a2 = (color2 shr 24) and 0xFF
        val r2 = (color2 shr 16) and 0xFF
        val g2 = (color2 shr 8) and 0xFF
        val b2 = color2 and 0xFF

        val a = (a1 + (a2 - a1) * f).toInt()
        val r = (r1 + (r2 - r1) * f).toInt()
        val g = (g1 + (g2 - g1) * f).toInt()
        val b = (b1 + (b2 - b1) * f).toInt()

        return (a shl 24) or (r shl 16) or (g shl 8) or b
    }

    fun setProgress(value: Int) {
        progress = value.coerceIn(0, max)
        val newColor = getColorForProgress(progress)
        progressPaint.color = newColor
        textPaint.color = newColor
        invalidate()
    }

    fun getProgress() = progress

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = MeasureSpec.getSize(widthMeasureSpec)
            .coerceAtMost(MeasureSpec.getSize(heightMeasureSpec))
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (width.coerceAtMost(height) / 2f) - (backgroundPaint.strokeWidth / 2f)

        // دایره پس‌زمینه
        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
        canvas.drawArc(rectF, 0f, 360f, false, backgroundPaint)

        // آرک پیشرفت
        val sweepAngle = (progress.toFloat() / max) * 360f
        canvas.drawArc(rectF, -90f, sweepAngle, false, progressPaint)

        // متن درصد
        val percentText = "${(progress * 100) / max}%"
        val textY = centerY - (textPaint.descent() + textPaint.ascent()) / 2f
        canvas.drawText(percentText, centerX, textY, textPaint)
    }
}