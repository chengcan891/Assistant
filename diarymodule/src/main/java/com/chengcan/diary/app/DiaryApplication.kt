package com.chengcan.diary.app

import androidx.room.Room
import com.chengcan.base.app.ApplicationLogic
import com.chengcan.diary.db.DiaryDatabase
import com.chengcan.log.Logger

class DiaryApplication : ApplicationLogic() {

    companion object {
        lateinit var db: DiaryDatabase
    }

    override fun onCreate() {
        super.onCreate()
        Logger.i("DiaryApplication", "onCreate")

        db = Room.databaseBuilder(
            mApplication,
            DiaryDatabase::class.java, "diary"
        ).build()
    }

}