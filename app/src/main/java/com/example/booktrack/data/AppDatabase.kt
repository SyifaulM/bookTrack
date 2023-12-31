package com.example.booktrack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booktrack.data.dao.BookDao
import com.example.booktrack.data.entity.Book

@Database(entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun bookDao(): BookDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}