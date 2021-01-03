package com.chengcan.base.utils

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.min


object BitmapUtil {

    /**
     * 转换bitmap 到byte数组
     */
    fun convertBitmapToByte(bitmap: Bitmap): ByteArray {
        val o = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, o)
        return o.toByteArray()
    }

    /**
     * 转换 byte array 到 Bitmap
     */
    fun convertByteToBitmap(bitmap: ByteArray): Bitmap? {
        return if (bitmap.isEmpty()) null else BitmapFactory.decodeByteArray(bitmap, 0, bitmap.size)
    }

    /**
     * 把bitmap转换成Base64编码String
     */
    fun bitmapToString(bitmap: Bitmap): String {
        return Base64.encodeToString(convertBitmapToByte(bitmap), Base64.DEFAULT)
    }

    /**
     * 转换 Drawable 到 Bitmap
     */
    fun convertDrawableToBitmap(drawable: Drawable): Bitmap {
        return (drawable as BitmapDrawable).bitmap
    }

    /**
     * 缩放 bitmap
     */
    fun scaleBitmap(bitmap: Bitmap, scaleWidth: Float, scaleHeight: Float): Bitmap {
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    /**
     * 把图片修建为圆形
     */
    fun toRoundCorner(bitmap: Bitmap): Bitmap {
        val value = min(bitmap.width, bitmap.height)
        val height = value
        val width = value
        val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val paint = Paint()
        val rect = Rect(0, 0, width, height)
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = Color.WHITE
//        paint.color = Color.TRANSPARENT
        canvas.drawCircle(width / 2.toFloat(), height / 2.toFloat(), width / 2.toFloat(), paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }

    /**
     * 创建缩略图
     *
     */
    fun createBitmapThumbnail(
        bitMap: Bitmap,
        newHeight: Int,
        newWidth: Int
    ): Bitmap {
        val width = bitMap.width
        val height = bitMap.height
        // 计算缩放比例
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        // 取得想要缩放的matrix参数
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        // 得到新的图片
        val newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true)
        return newBitMap
    }

    fun saveBitmap(bitmap: Bitmap, file: File): Boolean {
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.flush()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return false
    }

    fun saveBitmap(bitmap: Bitmap, absPath: String): Boolean {
        return saveBitmap(bitmap, File(absPath))
    }

    fun calculateInSampleSize(
        options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int
    ): Int {
        val h = options.outHeight
        val w = options.outWidth
        var inSampleSize = 0
        if (h > reqHeight || w > reqWidth) {
            val ratioW = w.toFloat() / reqWidth
            val ratioH = h.toFloat() / reqHeight
            inSampleSize = Math.min(ratioH, ratioW).toInt()
        }
        inSampleSize = Math.max(1, inSampleSize)
        return inSampleSize
    }

    fun getSmallBitmap(filePath: String, reqWidth: Int, reqHeight: Int): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, options)
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(filePath, options)
    }

    fun compressBitmapToBytes(
        filePath: String,
        reqWidth: Int,
        reqHeight: Int,
        quality: Int
    ): ByteArray {
        val bitmap = getSmallBitmap(filePath, reqWidth, reqHeight)
        val baos = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, quality, baos)
        val bytes = baos.toByteArray()
        return bytes
    }

    fun compressBitmapSmallTo(
        filePath: String,
        reqWidth: Int,
        reqHeight: Int,
        maxLenth: Int
    ): ByteArray? {
        var quality = 100
        var bytes = compressBitmapToBytes(filePath, reqWidth, reqHeight, quality)
        while (bytes.size > maxLenth && quality > 0) {
            quality /= 2
            bytes = compressBitmapToBytes(filePath, reqWidth, reqHeight, quality)
        }
        return bytes
    }

    fun rotateBitmap(bitmap: Bitmap, degrees: Float): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val matrix = Matrix()
        matrix.setRotate(degrees)
        // 围绕原地进行旋转
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false)
    }

    /**
     * 偏移效果
     */
    fun skewBitmap(origin: Bitmap): Bitmap {
        val width = origin.width
        val height = origin.height
        val matrix = Matrix()
        matrix.postSkew(-0.6f, -0.3f)
        return Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false)
    }
}