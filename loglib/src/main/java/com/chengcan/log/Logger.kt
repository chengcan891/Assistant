package com.chengcan.log

import android.util.Log


object Logger {

    fun i(tag: String, msg: String) {
        Log.i(tag, msg)
    }

    fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}