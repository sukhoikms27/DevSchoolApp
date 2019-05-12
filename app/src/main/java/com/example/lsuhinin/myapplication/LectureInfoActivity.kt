package com.example.lsuhinin.myapplication

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lsuhinin.myapplication.network.DevFestApi
import com.example.lsuhinin.myapplication.pojo.*
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.createSkeleton

class LectureInfoActivity : AppCompatActivity() {

    lateinit var topic: TextView
    lateinit var track: TextView
    lateinit var author: TextView
    lateinit var description: TextView

    lateinit var allLectures: Button
    lateinit var info: ImageView
    lateinit var lecture: LectureObj


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lecture_info_activity)

        topic = findViewById(R.id.topic)
        track = findViewById(R.id.track)
        description = findViewById(R.id.description)
        allLectures = findViewById(R.id.allLectures)
        author = findViewById(R.id.author)
        info = findViewById(R.id.info)

        lecture = intent.extras.get("lecture") as LectureObj
        displayLectureInfo(lecture)

        allLectures.setOnClickListener { openLecturesActivity() }
        author.setOnClickListener { openSpeakerInfoActivity(lecture) }
        info.setOnClickListener { openSpeakerInfoActivity(developer) }
    }

    fun displayLectureInfo(lecture: LectureObj) {
        topic.text = lecture.title
        track.let {
            when (lecture.track) {
                "android" -> {
                    it.text = lecture.track
                    it.setBackgroundResource(R.color.coral)
                }
                "frontend" -> {
                    it.text = lecture.track
                    it.setBackgroundResource(R.color.prismatic_blue)
                }
                "common" -> {
                    it.text = lecture.track
                    it.setBackgroundResource(R.color.violet)
                }
            }
        }

        author.text = "${lecture.speaker!!.firstName} ${lecture.speaker!!.lastName}" //FIXME разбить на два поля
        description.text = lecture.description
    }

    fun openLecturesActivity() {
        val lecturesListActivityIntent = Intent(this@LectureInfoActivity, LecturesListActivity::class.java)
        startActivity(lecturesListActivityIntent)
    }

    fun openSpeakerInfoActivity(lecture: LectureObj) {
        val speakerInfoActivityIntent = Intent(this@LectureInfoActivity, SpeakerInfoActivity::class.java)
        speakerInfoActivityIntent.putExtra("speaker", lecture)
        startActivity(speakerInfoActivityIntent)
    }
}
