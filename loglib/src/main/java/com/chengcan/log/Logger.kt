package com.chengcan.log

import android.util.Log


object Logger {

    private val logEnable: Boolean = true

    fun i(tag: String, msg: String) {
        if (logEnable) {
            Log.i(tag, msg)
        }

    }

    fun d(tag: String, msg: String) {
        if(logEnable){
            Log.d(tag, msg)
        }
    }
}