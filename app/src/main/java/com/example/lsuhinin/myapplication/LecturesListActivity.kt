package com.example.lsuhinin.myapplication

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lsuhinin.myapplication.adapter.LecturesAdapter
import com.example.lsuhinin.myapplication.api.getLectures
import com.example.lsuhinin.myapplication.network.Retrofit
import com.example.lsuhinin.myapplication.pojo.Lecture
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton

class LecturesListActivity : AppCompatActivity() {

    lateinit var lecturesRecyclerView: RecyclerView
    private lateinit var lecturesAdapter: LecturesAdapter
    private lateinit var skeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lectures_list_activity)
        initRecyclerView()

        LecturesListAsyncTask().execute()
    }

    fun initRecyclerView() {
        lecturesRecyclerView = findViewById(R.id.lectures_recycler_view)
        lecturesRecyclerView.layoutManager = LinearLayoutManager(this)

        val onLectureClickListener = object : LecturesAdapter.OnLectureClickListener {
            override fun onLectureClick(lecture: Lecture) {
                val intentLectureInfoActivity = Intent(this@LecturesListActivity, LectureInfoActivity::class.java)
                intentLectureInfoActivity.putExtra("lecture", lecture)
                startActivity(intentLectureInfoActivity)
            }
        }

        lecturesAdapter = LecturesAdapter(onLectureClickListener)
        lecturesRecyclerView.adapter = lecturesAdapter
    }

    inner class LecturesListAsyncTask : AsyncTask<Long, Int, Collection<Lecture>?>() {

        override fun onPreExecute() {
            super.onPreExecute()
            skeleton = lecturesRecyclerView.applySkeleton(R.layout.lecture_item_view, 4)
            skeleton.showSkeleton()
        }

        override fun onPostExecute(lectures: Collection<Lecture>?) {
            super.onPostExecute(lectures)
            skeleton.showOriginal()
            lectures?.let {
                lecturesAdapter.setItems(it)
            }
                    ?: Toast.makeText(this@LecturesListActivity, R.string.error, Toast.LENGTH_SHORT).show()
        }

        override fun doInBackground(vararg p0: Long?): Collection<Lecture>? {
            val response = Retrofit.getInstance().getResponse().execute().body()
            return response?.let { getLectures(it) }
        }


    }
}
