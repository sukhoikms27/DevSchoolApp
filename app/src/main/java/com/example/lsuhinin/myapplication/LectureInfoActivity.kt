package com.example.lsuhinin.myapplication

import android.content.ClipDescription
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.lsuhinin.myapplication.pojo.Lecture
import com.example.lsuhinin.myapplication.pojo.developer
import com.example.lsuhinin.myapplication.pojo.lectures

class LectureInfoActivity : AppCompatActivity() {

    lateinit var topic: TextView
    lateinit var track: TextView
    lateinit var author: TextView
    lateinit var description: TextView

    lateinit var allLectures: Button
    lateinit var info: ImageView
    var lectureId: Long? = 1L
    val LECTURE_ID = "lectureId"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lecture_info_activity)

        topic = findViewById(R.id.topic)
        track = findViewById(R.id.track)
        description = findViewById(R.id.description)
        allLectures = findViewById(R.id.allLectures)
        author = findViewById(R.id.author)
        info = findViewById(R.id.info)

        lectureId = intent.extras.get(LECTURE_ID) as Long ?: 1L
        displayLectureInfo(lectures.single { it.id == lectureId })

        allLectures.setOnClickListener { openLecturesActivity() }
        author.setOnClickListener { openSpeakerInfoActivity(lectures.single { it.id == lectureId }) }
        info.setOnClickListener { openSpeakerInfoActivity(developer) }
    }

    fun displayLectureInfo(lecture: Lecture) {
        topic.text = lecture.topic
        track.let {
            when (lecture.track) {
                "Android" -> {
                    it.text = lecture.track
                    it.setBackgroundResource(R.color.coral)
                }
                "Frontend" -> {
                    it.text = lecture.track
                    it.setBackgroundResource(R.color.prismatic_blue)
                }
                "Common" -> {
                    it.text = lecture.track
                    it.setBackgroundResource(R.color.violet)
                }
            }
        }

        author.text = lecture.speaker.name
        description.text = lecture.description
    }

    fun openLecturesActivity() {
        val lecturesListActivityIntent = Intent(this@LectureInfoActivity, LecturesListActivity::class.java)
        startActivity(lecturesListActivityIntent)
    }

    fun openSpeakerInfoActivity(lecture: Lecture) {
        val speakerInfoActivityIntent = Intent(this@LectureInfoActivity, SpeakerInfoActivity::class.java)
        speakerInfoActivityIntent.putExtra("speaker", lecture)
        startActivity(speakerInfoActivityIntent)
    }
}
