package com.chengcan.assistant.hilt

import android.util.Log
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject


interface Engine {
    fun on()
    fun off()
}

class ChinaEngine @Inject constructor() : Engine {
    override fun on() {
        Log.i("zrm", "ChinaEngine on")
    }

    override fun off() {
        Log.i("zrm", "ChinaEngine off")
    }
}

class ChinaCar @Inject constructor(val engine: Engine) {
    lateinit var name: String

    override fun toString(): String {
        return engine.toString()
    }
}

@Module
@InstallIn(ActivityComponent::class)//告诉Hilt 这个module属于的Component,ActivityComponent是Hilt定义好的
interface MainModule {

    @Binds
    fun bindEngine(chinaEngine: ChinaEngine): Engine

}