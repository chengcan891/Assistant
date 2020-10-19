package com.chengcan.base.app

import android.app.Application
import android.content.res.Configuration
import java.util.*


abstract class BaseApplication : Application() {

    private val logicClassList: ArrayList<Class<out ApplicationLogic>> = ArrayList()
    private val logicList: ArrayList<ApplicationLogic> = ArrayList()

    override fun onCreate() {
        super.onCreate()
        initLogic()
        logicCreate()

        for (logic in logicList) {
            logic.setApplication(this)
        }
        for (logic in logicList) {
            logic.onCreate()
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        for (baseAppLogic in logicList) {
            baseAppLogic.onTerminate()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        for (logic in logicList) {
            logic.onLowMemory()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        for (logic in logicList) {
            logic.onConfigurationChanged(newConfig)
        }
    }

    protected abstract fun initLogic()

    private fun logicCreate() {
        for (logicClazz in logicClassList) {
            try {
                val baseAppLogic: ApplicationLogic = logicClazz.newInstance()
                logicList.add(baseAppLogic)
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            }
        }
    }

    protected fun register(vararg baseLogicClazz: Class<out ApplicationLogic>) {
        this.logicClassList.addAll(Arrays.asList<Class<out ApplicationLogic>>(*baseLogicClazz))
    }
}