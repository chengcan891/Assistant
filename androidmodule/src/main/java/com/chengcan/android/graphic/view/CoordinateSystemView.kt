package com.chengcan.android.graphic.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CoordinateSystemView : View {
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
        val canvasWidth = getWidth();
        val canvasHeight = getHeight()
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(6 * density);

        //用绿色画x轴，用蓝色画y轴

        //第一次绘制坐标轴
        drawCoordinate(paint, canvas, canvasWidth, canvasHeight)

        //对坐标系平移后，第二次绘制坐标轴
        canvas.save()
        canvas.translate(canvasWidth.toFloat() / 4, canvasWidth.toFloat() /4);//把坐标系向右下角平移
        drawCoordinate(paint, canvas, canvasWidth, canvasHeight)
        canvas.restore()

        //再次平移坐标系并在此基础上旋转坐标系，第三次绘制坐标轴
        canvas.translate(canvasWidth.toFloat() / 2, canvasWidth.toFloat() / 2);//在上次平移的基础上再把坐标系向右下角平移
        canvas.rotate(30.toFloat());//基于当前绘图坐标系的原点旋转坐标系
        drawCoordinate(paint, canvas, canvasWidth, canvasHeight)

    }

    private fun drawCoordinate(
        paint: Paint,
        canvas: Canvas,
        canvasWidth: Int,
        canvasHeight: Int
    ) {
        paint.color = 0xff00ff00.toInt()//绿色
        canvas.drawLine(0.0F, 0.0F, canvasWidth.toFloat(), 0.0F, paint);//绘制x轴
        paint.color = 0xff0000ff.toInt()//蓝色
        canvas.drawLine(0.0F, 0.0F, 0.0F, canvasHeight.toFloat(), paint);//绘制y轴
    }


}
