package com.chengcan.diary.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chengcan.diary.db.Diary
import com.chengcan.diary.db.DiaryDao

@Database(entities = arrayOf(Diary::class), version = 1)
abstract class DiaryDatabase : RoomDatabase() {

    abstract fun diaryDao(): DiaryDao

}