package com.chengcan.assistant

import com.chengcan.base.app.BaseApplication
import com.chengcan.bug.CrashReport
import com.chengcan.bug.JavaCrashHandler
import com.chengcan.diary.app.DiaryApplication
import com.chengcan.language.app.LanguageApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AssistantApplication : BaseApplication() {


    override fun initLogic() {
        register(LanguageApplication::class.java, DiaryApplication::class.java)
    }

    override fun onCreate() {
        super.onCreate()

        CrashReport.init(this)

    }
}