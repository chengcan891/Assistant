package com.chengcan.language

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chengcan.language.db.Word
import com.chengcan.language.db.WordDao

@Database(entities = arrayOf(Word::class), version = 1)
abstract class WordDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

}