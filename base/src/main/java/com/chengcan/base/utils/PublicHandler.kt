package com.chengcan.base.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object PublicHandler {

    private val handler = Handler(Looper.getMainLooper())

    private var cachedThreadPool: ExecutorService = Executors.newCachedThreadPool()

    fun runOnUIThread(runnable: Runnable) {
        handler.post {
            runnable.run()
        }
    }

    fun runOnUIThread(runnable: Runnable, delayTime: Long) {
        handler.postDelayed({
            runnable.run()
        }, delayTime)
    }

    fun runOnWorkThread(runnable: Runnable) {
        cachedThreadPool.execute {
            runnable.run()
        }
    }

}