package com.example.lsuhinin.myapplication.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.lsuhinin.myapplication.R
import com.example.lsuhinin.myapplication.adapter.LecturesAdapter
import com.example.lsuhinin.myapplication.api.getLectures
import com.example.lsuhinin.myapplication.db.AppDatabase
import com.example.lsuhinin.myapplication.pojo.Lecture
import com.example.lsuhinin.myapplication.workers.UpdateDatabaseWorker
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import kotlinx.android.synthetic.main.fragment_lectures_list.*
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class LecturesListFragment : Fragment() {

    private lateinit var lecturesAdapter: LecturesAdapter
    private lateinit var skeleton: Skeleton
    private lateinit var listener: OnLectureSelected
    private var lecturesJob: Job? = null
    private var saveDataJob: Job? = null
    val updateDatabaseWorker = PeriodicWorkRequestBuilder<UpdateDatabaseWorker>(1, TimeUnit.DAYS).build()


    companion object {
        fun newInstance(): LecturesListFragment = LecturesListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        retainInstance = true
        return inflater.inflate(R.layout.fragment_lectures_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lectures_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            lecturesAdapter = LecturesAdapter(listener)
            adapter = lecturesAdapter

        }
        skeleton = lectures_recycler_view.applySkeleton(R.layout.lecture_item_view, 4)
        skeleton.showSkeleton()

        lecturesJob = GlobalScope.launch {
            val response = getLectures()
            withContext(Dispatchers.Main) {
                skeleton.showOriginal()
                response?.let { lecturesAdapter.setItems(it) }
                        ?: lecturesAdapter.setItems(restoreData())
            }
        }

        WorkManager.getInstance().enqueue(updateDatabaseWorker)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnLectureSelected) {
            listener = context
        } else {
            throw ClassCastException("$context must implement OnLectureSelected")
        }
    }

    override fun onDestroy() {
        lecturesJob?.cancel()
        saveDataJob = GlobalScope.launch {
            saveData(lecturesAdapter.getItems())
        }
        super.onDestroy()
    }

    fun saveData(lectures: Collection<Lecture>) {
        context?.let {
            AppDatabase.getInstance(it).apply {
                this.let {
                    lectureDao().run {
                        deleteAllLectures()
                        insertAll(*lectures.toTypedArray())
                    }
                }
        }
        }
    }

    fun restoreData(): Collection<Lecture> {
        var db = AppDatabase.getInstance(context!!)
        return db?.lectureDao()!!.getAllLectures()

    }

    interface OnLectureSelected {
        fun onLectureSelected(lecture: Lecture)
    }
}
