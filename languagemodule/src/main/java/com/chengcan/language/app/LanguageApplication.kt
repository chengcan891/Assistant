package com.chengcan.language.app

import androidx.room.Room
import com.chengcan.base.app.ApplicationLogic
import com.chengcan.language.WordDatabase
import com.chengcan.log.Logger

class LanguageApplication: ApplicationLogic() {

    companion object {
        lateinit var db: WordDatabase
    }

    override fun onCreate() {
        super.onCreate()
        Logger.i("LanguageApplication","onCreate")

        db = Room.databaseBuilder(
            mApplication,
            WordDatabase::class.java, "language"
        ).build()
    }

}