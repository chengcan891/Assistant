package com.chengcan.android.graphic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chengcan.android.R
import com.chengcan.base.BaseFragment
import com.chengcan.base.utils.DensityUtil
import kotlinx.android.synthetic.main.androidmodule_fragment_display.*
import kotlin.math.sqrt


/**
 * A simple [Fragment] subclass.
 * Use the [CanvasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DisplayFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.androidmodule_fragment_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val metrics = resources.displayMetrics

        val sb = StringBuilder()
        sb.append("px:Pixels像素 - 对应于屏幕上的实际像素数\n")
        sb.append("屏幕像素：宽")
        sb.append(metrics.widthPixels).append("px 高：").append(metrics.heightPixels).append("px")
        sb.append("\n\ndpi:dot per inch每英寸像素数")
        sb.append("\nxdpi:").append(metrics.xdpi).append("ydpi:").append(metrics.ydpi)
        sb.append("\n屏幕尺寸：").append(
            sqrt(
                ((metrics.widthPixels / metrics.xdpi) * (metrics.widthPixels / metrics.xdpi) +
                        (metrics.heightPixels / metrics.ydpi) * (metrics.heightPixels / metrics.ydpi)).toDouble()
            )
        ).append("英寸")
        sb.append("\n\ndensityDpi:").append(metrics.densityDpi).append(" 用于适配屏幕，程序的各种资源通过这个适配")

        sb.append("\n\ndensity:").append(metrics.density).append(" 1dp对应像素数")
            .append("\nwidthPixels/density:").append(metrics.widthPixels / metrics.density).append("dp 屏幕宽真实的dp")

        sb.append("\n\nsp:Scale-independent Pixels 默认和dp一样" +
                "\nscaledDensity和density默认一致，系统字体修改，就不一样了" +
                "\nscaledDensity").append(metrics.scaledDensity)
            .append("\nwidthPixels/scaledDensity:").append(metrics.widthPixels/metrics.scaledDensity).append("sp 屏幕宽sp")

        sb.append("\n\n1英寸等于2.54厘米")
            .append("\n每厘米上的点:xdpi/2.54=").append(metrics.xdpi/2.54)

        start.setText(sb.toString())

        context?.let { DensityUtil.getPingMuSize(it) }

    }
}

