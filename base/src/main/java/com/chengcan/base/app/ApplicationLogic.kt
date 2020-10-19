package com.chengcan.base.app

import android.content.res.Configuration

open class ApplicationLogic {

    protected lateinit var mApplication: BaseApplication

    fun setApplication(baseApplication: BaseApplication) {
        mApplication = baseApplication
    }


    open fun onCreate() {}


    open fun onTerminate() {}


    open fun onLowMemory() {}


    open fun onConfigurationChanged(newConfig: Configuration) {}
}