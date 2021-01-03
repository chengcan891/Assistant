package com.chengcan.base.utils

object NumberUtil {

    fun parseLong(text: String, default: Long): Long {
        try {
            return java.lang.Long.parseLong(text)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return default
    }

    fun parseInt(text: String, default: Int): Int {
        try {
            return Integer.parseInt(text)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return default
    }

    fun parseDouble(text: String, default: Double): Double {
        try {
            return java.lang.Double.parseDouble(text)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return default
    }
}