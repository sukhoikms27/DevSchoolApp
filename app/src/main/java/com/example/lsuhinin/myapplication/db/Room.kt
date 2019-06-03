package com.example.lsuhinin.myapplication.db

import android.content.Context
import androidx.room.*
import com.example.lsuhinin.myapplication.pojo.Lecture

@Database(entities = [Lecture::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lectureDao(): LectureDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        fun buildDatabase(context: Context) =
                Room.databaseBuilder(context, AppDatabase::class.java, "lecturesdb")
                        .build()


        fun destroyInstance() {
            instance = null
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




