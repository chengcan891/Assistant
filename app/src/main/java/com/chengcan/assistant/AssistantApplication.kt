package com.chengcan.assistant

import androidx.room.Room
import com.chengcan.base.app.BaseApplication
import com.chengcan.diary.app.DiaryApplication
import com.chengcan.language.WordDatabase
import com.chengcan.language.app.LanguageApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AssistantApplication : BaseApplication() {


    override fun initLogic() {
        register(LanguageApplication::class.java, DiaryApplication::class.java)
    }

    override fun onCreate() {
        super.onCreate()

    }
}