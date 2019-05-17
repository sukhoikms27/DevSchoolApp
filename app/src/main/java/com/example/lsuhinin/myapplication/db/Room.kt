package com.example.lsuhinin.myapplication.db

import android.content.Context
import androidx.room.*
import com.example.lsuhinin.myapplication.pojo.Lecture
import com.example.lsuhinin.myapplication.pojo.Links
import com.example.lsuhinin.myapplication.pojo.Speaker

@Database(entities = [Lecture::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun lectureDao(): LectureDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, "lectures.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}

@Dao
interface LectureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLecture(lecture: Lecture)

    @Insert
    fun insertAll(vararg lectures: Lecture)

    @Update
    fun updateLecture(lecture: Lecture)

    @Delete
    fun deleteLecture(lecture: Lecture)

    @Query("DELETE from Lecture")
    fun deleteAllLectures()

    @Query("SELECT * FROM Lecture")
    fun getAllLectures(): List<Lecture>
}




