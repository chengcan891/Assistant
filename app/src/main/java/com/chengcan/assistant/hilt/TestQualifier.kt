package com.chengcan.assistant.hilt

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MadeInCN

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MadeInUSA

class AmericaEngine @Inject constructor():Engine{
    override fun on() {
        Log.i("zrm", "AmericaEngine on")
    }
    override fun off() {
        Log.i("zrm", "AmericaEngine off")
    }
}

@Module
@InstallIn(ActivityComponent::class)
class TestQualifierModule {
    @Provides
    @MadeInCN
    fun provideChinaCar():ChinaCar
    {
        return ChinaCar(ChinaEngine())
    }

    @Provides
    @MadeInUSA
    fun provideChinaCar2():ChinaCar
    {
        return ChinaCar(AmericaEngine())
    }
}