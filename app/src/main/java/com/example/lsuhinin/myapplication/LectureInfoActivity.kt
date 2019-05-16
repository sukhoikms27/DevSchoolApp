package com.example.lsuhinin.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lsuhinin.myapplication.pojo.*
import com.google.android.material.chip.Chip

class LectureInfoActivity : AppCompatActivity() {

    lateinit var topic: TextView
    lateinit var track: Chip
    lateinit var author: TextView
    lateinit var description: TextView

    lateinit var allLectures: Button
    lateinit var info: ImageView
    lateinit var lecture: Lecture


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lecture_info_activity)

        topic = findViewById(R.id.topic)
        track = findViewById(R.id.track)
        description = findViewById(R.id.description)
        allLectures = findViewById(R.id.allLectures)
        author = findViewById(R.id.author)
        info = findViewById(R.id.info)

        lecture = intent.extras.get("lecture") as Lecture
        displayLectureInfo(lecture)

        allLectures.setOnClickListener { openLecturesActivity() }
        author.setOnClickListener { openSpeakerInfoActivity(lecture) }
        info.setOnClickListener { openSpeakerInfoActivity(DEVELOPER) }
    }

    fun displayLectureInfo(lecture: Lecture) {
        topic.text = lecture.title
        track.let {
            when (lecture.track) {
                "android" -> {
                    it.text = "Android"
                    it.setChipIconResource(R.drawable.shape_oval_coral)
                }
                "frontend" -> {
                    it.text = "Frontend"
                    it.setChipIconResource(R.drawable.shape_oval_prismatic_blue)
                }
                "common" -> {
                    it.text = "Common"
                    it.setChipIconResource(R.drawable.shape_oval_violet)
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

    fun openSpeakerInfoActivity(lecture: Lecture) {
        val speakerInfoActivityIntent = Intent(this@LectureInfoActivity, SpeakerInfoActivity::class.java)
        speakerInfoActivityIntent.putExtra("speaker", lecture)
        startActivity(speakerInfoActivityIntent)
    }
}
