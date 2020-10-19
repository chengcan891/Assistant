package com.chengcan.diary.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiaryDao {

    @Query("SELECT * FROM DIARY WHERE time >= :minTime AND time <:maxTime")
    fun queryDiary(minTime: Long, maxTime:Long): List<Diary>

    @Query("SELECT * FROM DIARY")
    fun getAll(): List<Diary>

    @Insert
    fun insert(diary: Diary)

    @Delete
    fun delete(diary: Diary)

}