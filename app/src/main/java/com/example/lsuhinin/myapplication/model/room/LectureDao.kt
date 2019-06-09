package com.example.lsuhinin.myapplication.model.room

import androidx.room.*
import com.example.lsuhinin.myapplication.model.Lecture


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