package com.idan.app.module.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.TextView


/**
 * createUser : Administrator
 * createDate : 2020/03/10
 * remark     :
 */
class KButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {
   private val mPaint = Paint()
    init {
        mPaint.color = Color.RED
        mPaint.isAntiAlias  =true
        mPaint.isDither = true
        mPaint.strokeWidth = 1f
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRoundRect(RectF(0f,0f, width.toFloat(), height.toFloat()),height.toFloat() /2,height.toFloat()/2,mPaint)
        canvas?.translate(width.toFloat() / 2, height.toFloat() / 2);
        canvas?.drawText(text.toString(), -paint.measureText(text.toString()) / 2,  Math.abs(paint.ascent() + paint.descent()) / 2, paint)
    }


}