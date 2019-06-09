package com.example.lsuhinin.myapplication.ui.lectureslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lsuhinin.myapplication.R
import com.example.lsuhinin.myapplication.helpers.isConnectedToInternet
import com.example.lsuhinin.myapplication.model.Lecture
import com.example.lsuhinin.myapplication.model.getLectures
import com.example.lsuhinin.myapplication.model.network.Response
import com.example.lsuhinin.myapplication.model.network.Retrofit
import com.example.lsuhinin.myapplication.model.room.LecturesDatabase
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import kotlinx.android.synthetic.main.fragment_lectures_list.*
import kotlinx.coroutines.*

class LecturesListFragment : Fragment() {

    private lateinit var lecturesAdapter: LecturesAdapter
    private lateinit var skeleton: Skeleton
    private lateinit var listener: OnLectureSelected
    private var lecturesJob: Job? = null
    private lateinit var lectures: Collection<Lecture>
//fixme disable for debug
//    val updateDatabaseWorker = PeriodicWorkRequestBuilder<UpdateDatabaseWorker>(1, TimeUnit.DAYS).build()


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
            if (context!!.isConnectedToInternet()) {
                val response: Response? = Retrofit.getInstance().getResponse().await()
                lectures = response!!.let { getLectures(it) }
                saveData(lectures)
            } else {
                lectures = restoreData()
            }

            withContext(Dispatchers.Main) {
                skeleton.showOriginal()

                lecturesAdapter.setItems(lectures)
            }
        }
//fixme disable for debug
//        WorkManager.getInstance().enqueue(updateDatabaseWorker)
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
        super.onDestroy()
    }

    fun saveData(lectures: Collection<Lecture>) {
        context?.let {
            LecturesDatabase.getInstance(it).apply {
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
        val db = LecturesDatabase.getInstance(context!!)
        return db.lectureDao().getAllLectures()

    }

    interface OnLectureSelected {
        fun onLectureSelected(lecture: Lecture)
    }
}
