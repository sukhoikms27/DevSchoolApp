package com.example.lsuhinin.myapplication.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lsuhinin.myapplication.R
import com.example.lsuhinin.myapplication.adapter.LecturesAdapter
import com.example.lsuhinin.myapplication.api.getLectures
import com.example.lsuhinin.myapplication.db.AppDatabase
import com.example.lsuhinin.myapplication.pojo.Lecture
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import kotlinx.android.synthetic.main.fragment_lectures_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LecturesListFragment : Fragment() {

    private lateinit var lecturesAdapter: LecturesAdapter
    private lateinit var skeleton: Skeleton
    private lateinit var listener: OnLectureSelected


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

        GlobalScope.launch {
            val response = getLectures()
            withContext(Dispatchers.Main) {
                skeleton.showOriginal()
                response?.let { lecturesAdapter.setItems(it) }
                        ?: Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnLectureSelected) {
            listener = context
        } else {
            throw ClassCastException("$context must implement OnLectureSelected")
        }
    }

    fun saveData(lectures: Collection<Lecture>) {
        AppDatabase.getAppDataBase(context!!).apply {
            this?.let {
                lectureDao().run {
                    deleteAllLectures()
                    insertAll(*lectures.toTypedArray())
                }
            }
        }
    }

    fun restoreData(): Collection<Lecture> {
        var db = AppDatabase.getAppDataBase(context!!)
        return db?.lectureDao()!!.getAllLectures()

    }

    interface OnLectureSelected {
        fun onLectureSelected(lecture: Lecture)
    }
}
