package com.chengcan.android.graphic.view

import android.R
import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import com.chengcan.base.utils.BitmapUtil
import com.chengcan.base.utils.DensityUtil
import com.chengcan.log.Logger


/**
 * 一：一般使用
 * https://www.jianshu.com/p/a54b758803c9
 *
 * 1.指定中间位置，绘制文本
 * float baselineY = centerY + (fontMetrics.bottom-fontMetrics.top)/2 - fontMetrics.bottom
2.指定左上角的顶点坐标 绘制文本
float baselineY = Y - fontMetrics.top;
 * 二：文字的换行
 * TextPaint/StaticLayout
 */
class DrawTextView : View {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }


    override fun draw(canvas: Canvas) {
        super.draw(canvas)


        val paint1 = Paint()
        paint1.textSize = DensityUtil.dp2px(context, 14f).toFloat()
        val text = "你好abc"
        paint1.color = Color.GREEN
        canvas.save()
        //1.颜色和透明度
        var texttop = 0 - paint1.fontMetrics.top
        var textHeight = paint1.fontMetrics.bottom - paint1.fontMetrics.top

        canvas.drawText(
            text, 0f, texttop, //指定左上角的顶点坐标
            paint1
        )
        paint1.setARGB(255, 255, 0, 0)
        texttop += textHeight
        canvas.drawText(
            text, 0f, texttop, //指定左上角的顶点坐标
            paint1
        )
        paint1.alpha = (0.5 * 255).toInt()
        texttop += textHeight
        canvas.drawText(
            text, 0f, texttop, //指定左上角的顶点坐标
            paint1
        )


        //2.设置样式
        paint1.reset()
        texttop += textHeight + textHeight
        paint1.color = Color.RED
        paint1.textSize = DensityUtil.dp2px(context, 24f).toFloat()
        paint1.style = Paint.Style.FILL //填充
        canvas.drawText(
            text, 0f, texttop, //指定左上角的顶点坐标
            paint1
        )

        textHeight = paint1.fontMetrics.bottom - paint1.fontMetrics.top
        paint1.style = Paint.Style.STROKE //空心
        texttop += textHeight
        canvas.drawText(
            text, 0f, texttop, //指定左上角的顶点坐标
            paint1
        )
        paint1.style = Paint.Style.FILL_AND_STROKE //填充+轮廓
        paint1.strokeWidth = 10f //设置画笔宽度
        texttop += textHeight
        canvas.drawCircle(100f, texttop, 50f, paint1)
        paint1.color = Color.GREEN
        paint1.style = Paint.Style.FILL//填充
        canvas.drawCircle(125f, texttop, 50f, paint1)
        paint1.color = Color.BLUE
        paint1.style = Paint.Style.STROKE//轮廓
        canvas.drawCircle(150F, texttop, 50f, paint1)
        //设置线帽
        paint1.strokeWidth = 40f //设置画笔宽度
        texttop += 100f
        paint1.setStrokeCap(Paint.Cap.BUTT)
        canvas.drawLine(100f, texttop, width.toFloat() - 100, texttop, paint1)
        texttop += 50f
        paint1.setStrokeCap(Paint.Cap.ROUND)
        canvas.drawLine(100f, texttop, width.toFloat() - 100, texttop, paint1)
        texttop += 50f
        paint1.setStrokeCap(Paint.Cap.SQUARE)
        canvas.drawLine(100f, texttop, width.toFloat() - 100, texttop, paint1)

        //设置线段连接处样式
        paint1.strokeWidth = 20f //设置画笔宽度
        paint1.strokeJoin = Paint.Join.MITER//（结合处为锐角）
        texttop += 60f
        canvas.drawRect(100f, texttop, width.toFloat() - 100, texttop + 30, paint1)
        paint1.strokeJoin = Paint.Join.ROUND//(结合处为圆弧)
        texttop += 60f
        canvas.drawRect(100f, texttop, width.toFloat() - 100, texttop + 30, paint1)
        paint1.strokeJoin = Paint.Join.BEVEL//(结合处为直线)
        texttop += 60f
        canvas.drawRect(100f, texttop, width.toFloat() - 100, texttop + 30, paint1)


        Logger.i("DrawTextView", "getFontSpacing" + paint1.getFontSpacing())

        val paint = Paint()
        val density = resources.displayMetrics.density
        val canvasWidth = width
        val canvasHeight = height
        paint.style = Paint.Style.FILL
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = density
        paint.textSize = DensityUtil.dp2px(context, 128f).toFloat()


        //绘制正常文本
        canvas.save()
        val offset = texttop + 400


        val top = paint.fontMetrics.top
        val ascent = paint.fontMetrics.ascent
        val descent = paint.fontMetrics.descent
        val bottom = paint.fontMetrics.bottom
        Logger.i("DrawTextView", "size" + paint.textSize)
        Logger.i("DrawTextView", "top" + top)
        Logger.i("DrawTextView", "ascent" + ascent)
        Logger.i("DrawTextView", "descent" + descent)
        Logger.i("DrawTextView", "bottom" + paint.fontMetrics.bottom)
        Logger.i("DrawTextView", "bottom-top" + (paint.fontMetrics.bottom - paint.fontMetrics.top))
        Logger.i(
            "DrawTextView",
            "(bottom-top)/2-bottom" + ((paint.fontMetrics.bottom - paint.fontMetrics.top) / 2 - paint.fontMetrics.bottom)
        )

        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        Logger.i("DrawTextView", "bounds" + bounds.toString())

        val widths = FloatArray(text.length)
        paint1.getTextWidths(text, widths)
        var totalWidth = 0.0
        for (width in widths) {
            Logger.i("DrawTextView", "width:" + width)
            totalWidth += width
        }
        val width2 = paint1.measureText(text)
        Logger.i("DrawTextView", "totalWidth:" + (totalWidth * resources.displayMetrics.density))
        Logger.i("DrawTextView", "bound totalWidth:" + (bounds.right - bounds.left))
        Logger.i("DrawTextView", "measureText width:" + width2 * resources.displayMetrics.density)
        canvas.drawLine(
            bounds.right.toFloat(),
            0.0f,
            bounds.right.toFloat(),
            height.toFloat(),
            paint1
        )

        val text2 = "你好abcdefg"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Logger.i("DrawTextView", "letterSpacing:" + paint1.letterSpacing)
        }
        val sss = FloatArray(text.length)
        val countChar = paint1.breakText(text2, true, width2, sss)
        Logger.i("DrawTextView", "countChar:" + countChar)
        Logger.i("DrawTextView", "sss:" + sss[0] * resources.displayMetrics.density)

        val textPaint = TextPaint()
        textPaint.setARGB(0xFF, 0xFF, 0, 0)
        textPaint.textSize = 40.0f
        val aboutTheGame = "关于本游戏：本游戏是做测试用的，这些文字也是，都不是瞎写的！ "
//        val layout =
//            StaticLayout(
//                aboutTheGame,
//                textPaint,
//                240,
//                Layout.Alignment.ALIGN_NORMAL,
//                1.0f,
//                0.0f,
//                true
//            )

        val aaa = TextUtils.TruncateAt.END

        val layout = StaticLayout(
            aboutTheGame,
            0,
            aboutTheGame.length,
            textPaint,
            300,
            Layout.Alignment.ALIGN_NORMAL,
            1.0f,
            0.0f,
            true,
            aaa, 5
        )

        Logger.i("DrawTextView", "static height;:" + layout.height)

        canvas.save()
        canvas.translate(300f, 0f)
        layout.draw(canvas)
        canvas.restore()
        canvas.save()
        canvas.translate(300f, layout.height.toFloat())
        layout.draw(canvas)
        canvas.restore()

        paint.color = Color.RED
        canvas.drawLine(0F, top + offset, width.toFloat(), top + offset, paint)
        paint.color = Color.BLACK
        canvas.drawLine(0F, ascent + offset, width.toFloat(), ascent + offset, paint)
        paint.color = Color.RED
        canvas.drawLine(0F, offset, width.toFloat(), offset, paint)
        paint.color = Color.BLACK
        canvas.drawLine(0F, descent + offset, width.toFloat(), descent + offset, paint)
        paint.color = Color.RED
        canvas.drawLine(0F, bottom + offset, width.toFloat(), bottom + offset, paint)

        paint.color = Color.GREEN
        canvas.drawLine(
            0F,
            bounds.top.toFloat() + offset,
            width.toFloat(),
            bounds.top.toFloat() + offset,
            paint
        )
        canvas.drawLine(
            0F,
            bounds.bottom.toFloat() + offset,
            width.toFloat(),
            bounds.bottom.toFloat() + offset,
            paint
        )

        canvas.drawText(text, 0.0f, offset, paint)
        paint.setARGB(255, 100, 100, 100)

        var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.sym_def_app_icon)
        canvas.drawBitmap(bitmap,600f,50f,paint)
        val width: Int = bitmap.getWidth()
        val height: Int = bitmap.getHeight()
// 设置想要的大小
// 设置想要的大小
        val newWidth = 96
        val newHeight = 128
// 计算缩放比例
// 计算缩放比例
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
// 取得想要缩放的matrix参数
// 取得想要缩放的matrix参数
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
// 得到新的图片
        // 得到新的图片
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
        val colorMatrix = ColorMatrix(
            floatArrayOf(
                1f, 0f, 0f, 0f, 0f,
                0f, 0.3f, 0f, 0f, 0f,
                0f, 0f, 1f, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
            )
        )
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitmap,800f,50f,paint)

        val b = BitmapUtil.toRoundCorner(bitmap)
        canvas.drawBitmap(b,900f,50f,paint)
        val bThumbnail = BitmapUtil.createBitmapThumbnail(bitmap, 40,
        40)
        canvas.drawBitmap(bThumbnail,1000f,50f,paint)

        val bRotate= BitmapUtil.rotateBitmap(bitmap, 45f)
        canvas.drawBitmap(bRotate,600f,150f,paint)
        val skewBitmap= BitmapUtil.skewBitmap(bitmap)
        canvas.drawBitmap(skewBitmap,700f,150f,paint)

        val a = (bottom - top) / density
        Logger.i("DrawTextView", "height:" + a)
        Logger.i("DrawTextView", "height:" + (bottom - top))
        Logger.i("DrawTextView", "bounds height:" + (bounds.bottom - bounds.top))
        Logger.i("DrawTextView", "density:" + resources.displayMetrics.density)
        Logger.i("DrawTextView", "paint.textSize:" + paint.textSize)

        canvas.restore()


    }


}
