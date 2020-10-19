package com.chengcan.diary.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DIARY")
data class Diary(
    @PrimaryKey  val time: Long,
    @ColumnInfo(name = "content") val text: String?
)