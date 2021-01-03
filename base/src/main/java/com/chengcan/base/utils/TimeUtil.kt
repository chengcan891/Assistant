package com.chengcan.base.utils

object TimeUtil {

    /**
     * 02:11:11
     */
    fun parseTime(time: String): Long {
        val times = time.split(":")
        if (times.size != 3) {
            return 0
        }
        val h = NumberUtil.parseLong(times[0], 0)
        val m = NumberUtil.parseLong(times[1], 0)
        val s = NumberUtil.parseLong(times[2], 0)
        return ((h * 60 + m) * 60 + s) * 1000
    }
}