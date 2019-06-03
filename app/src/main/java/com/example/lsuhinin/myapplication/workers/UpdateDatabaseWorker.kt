package com.example.lsuhinin.myapplication.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.lsuhinin.myapplication.api.getLectures
import com.example.lsuhinin.myapplication.db.AppDatabase
import com.example.lsuhinin.myapplication.pojo.Lecture
import kotlinx.coroutines.*

class UpdateDatabaseWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = coroutineScope  {
        val jobs =(0 until 100).map {
            async {
                getLectures()?.let { lectures -> saveData(lectures) } ?: Result.failure()
            }
        }
        jobs.awaitAll()
        Result.success()
    }

    fun saveData(lectures: Collection<Lecture>) {
        AppDatabase.getInstance(applicationContext).apply {
            this.let {
                lectureDao().run {
                    deleteAllLectures()
                    insertAll(*lectures.toTypedArray())
                }
            }
        }
    }
}