package com.chengcan.language.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey
    val word: String,
    @ColumnInfo(name = "mnemonics") val mnemonics: String
)
