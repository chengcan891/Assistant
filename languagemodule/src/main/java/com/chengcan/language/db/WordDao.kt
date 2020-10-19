package com.chengcan.language.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {


    @Query("SELECT * FROM Word")
    fun getAll(): List<Word>

    @Insert
    fun insert(word: Word)

    @Delete
    fun delete(word: Word)

}