package com.example.booktrack.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "title") var book_title: String?,
    @ColumnInfo(name = "writer") var book_writer: String?,
    @ColumnInfo(name = "publisher") var book_pub: String?,
    @ColumnInfo(name = "page") var book_page: String?,
    @ColumnInfo(name = "curr") var curr_page: Int?,
    @ColumnInfo(name = "desc") var book_desc: String?,
    @ColumnInfo(name = "rate") var book_rate: Int?
)