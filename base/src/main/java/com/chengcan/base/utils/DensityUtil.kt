package com.chengcan.base.utils

import android.content.Context
import com.chengcan.log.Logger

object DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dp2px(context: Context, dpValue: Float): Int {
        val scale: Float = context.getResources().getDisplayMetrics().density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dp(context: Context, pxValue: Float): Int {
        val scale: Float = context.getResources().getDisplayMetrics().density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * px:pixel 像素
     * dpi:dot per inch 每英寸像素多少
     * dip/dp: Density-independent Pixels
     * sp: Scale-independent Pixel
     */
    fun getPingMuSize(mContext: Context) {
        val densityDpi = mContext.resources.displayMetrics.densityDpi
        Logger.i("Sreen", "densityDpi:$densityDpi") //480
        val scaledDensity = mContext.resources.displayMetrics.scaledDensity
        Logger.i("Sreen", "scaledDensity:$scaledDensity") //3.0
        val density = mContext.resources.displayMetrics.density
        Logger.i("Sreen", "density:$density") //3.0
        val xdpi = mContext.resources.displayMetrics.xdpi
        Logger.i("Sreen", "xdpi:$xdpi") //428.625 每英寸上的点
        val ydpi = mContext.resources.displayMetrics.ydpi
        Logger.i("Sreen", "ydpi:$ydpi") //427.789 每英寸上的点
        val width = mContext.resources.displayMetrics.widthPixels
        Logger.i("Sreen", "width:$width") //1080 像素
        val height = mContext.resources.displayMetrics.heightPixels
        Logger.i("Sreen", "height:$height") //1920 像素
        val width2 = width / xdpi * (width / xdpi)
        val height2 = height / ydpi * (height / xdpi)
        Logger.i(
            "Sreen",
            "size:" + Math.sqrt(width2 + height2.toDouble()).toFloat()
        ) //5.1432877   屏幕的尺寸


        //1英寸等于2.54厘米
        Logger.i("Sreen", "cm:" + xdpi / 2.54) //168.75 每厘米上的点
        val screenWidth = (width / density).toInt() // 屏幕宽度(dp)  360
    }
}