package com.chengcan.assistant.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

data class Dog(val name:String)

@Module
@InstallIn(ActivityComponent::class)
class DogModule
{
    @Provides
    fun provideDog()=Dog("京巴犬")
}