package com.example.lsuhinin.myapplication.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lsuhinin.myapplication.model.Lecture

@Database(entities = [Lecture::class], version = 1)
abstract class LecturesDatabase : RoomDatabase() {

    abstract fun lectureDao(): LectureDao

    companion object {

        @Volatile
        private var instance: LecturesDatabase? = null

        fun getInstance(context: Context): LecturesDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        fun buildDatabase(context: Context) =
                Room.databaseBuilder(context, LecturesDatabase::class.java, "lecturesdb")
                        .build()


        fun destroyInstance() {
            instance = null
        }
    }

}