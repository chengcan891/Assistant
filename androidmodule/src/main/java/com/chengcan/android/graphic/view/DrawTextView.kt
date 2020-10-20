package com.chengcan.android.graphic.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.chengcan.base.utils.DensityUtils

class DrawTextView : View {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){}



    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        val paint = Paint()
        val density = resources.displayMetrics.density
        val canvasWidth = width
        val canvasHeight = height
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 6 * density
        paint.textSize = DensityUtils.dp2px(context, 16f).toFloat()


        //绘制正常文本
        canvas.save()
        canvas.drawText("正常绘制文本", 0.0f, 0.0f, paint);
        canvas.restore();







    }


}
