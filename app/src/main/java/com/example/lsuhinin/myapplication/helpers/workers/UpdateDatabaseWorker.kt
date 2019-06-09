package com.example.lsuhinin.myapplication.helpers.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.lsuhinin.myapplication.model.Lecture
import com.example.lsuhinin.myapplication.model.getLectures
import com.example.lsuhinin.myapplication.model.network.Retrofit
import com.example.lsuhinin.myapplication.model.room.LecturesDatabase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class UpdateDatabaseWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = coroutineScope {
        val jobs = (0 until 100).map {
            async {
                val response = Retrofit.getInstance().getResponse().await()
                getLectures(response)?.let { lectures -> saveData(lectures) } ?: Result.failure()
            }
        }
        jobs.awaitAll()
        Result.success()
    }

    fun saveData(lectures: Collection<Lecture>) {
        LecturesDatabase.getInstance(applicationContext).apply {
            this.let {
                lectureDao().run {
                    deleteAllLectures()
                    insertAll(*lectures.toTypedArray())
                }
            }
        }
    }
}