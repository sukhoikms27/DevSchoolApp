package com.example.lsuhinin.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.lsuhinin.myapplication.adapter.LecturesAdapter
import com.example.lsuhinin.myapplication.pojo.Lecture
import com.example.lsuhinin.myapplication.pojo.lectures

class LecturesListActivity : AppCompatActivity() {

    lateinit var lecturesRecyclerView: RecyclerView
    private lateinit var lecturesAdapter: LecturesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lectures_list_activity)
        initRecyclerView()
        loadLections()

    }

    fun initRecyclerView() {
        lecturesRecyclerView = findViewById(R.id.lectures_recycler_view)
        lecturesRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lecturesRecyclerView.layoutManager = LinearLayoutManager(this)

        val onLectureClickListener = object : LecturesAdapter.OnLectureClickListener {
            override fun onLectureClick(lecture: Lecture) {
                val intentLectureInfoActivity = Intent(this@LecturesListActivity, LectureInfoActivity::class.java)
                intentLectureInfoActivity.putExtra(LectureInfoActivity().LECTURE_ID, lecture.id)
                startActivity(intentLectureInfoActivity)
            }
        }

        lecturesAdapter = LecturesAdapter(onLectureClickListener)
        lecturesRecyclerView.adapter = lecturesAdapter
    }

    fun loadLections() {
        lecturesAdapter.setItems(lectures)
    }
}
