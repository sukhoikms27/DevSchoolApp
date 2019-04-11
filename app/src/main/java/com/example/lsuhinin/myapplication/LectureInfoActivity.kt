package com.example.lsuhinin.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.lsuhinin.myapplication.pojo.Speaker
import com.example.lsuhinin.myapplication.pojo.developer
import com.example.lsuhinin.myapplication.pojo.shishkinEvg

class LectureInfoActivity : AppCompatActivity() {

    lateinit var allLectures: Button
    lateinit var author: TextView
    lateinit var info: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lecture_info_activity)

        allLectures = findViewById(R.id.allLectures)
        author = findViewById(R.id.author)
        info = findViewById(R.id.info)

        allLectures.setOnClickListener { openLecturesActivity() }
        author.setOnClickListener { openSpeakerInfoActivity() }
        info.setOnClickListener { openAuthorInfo() }
    }

    fun openLecturesActivity() {
        val lecturesListActivityIntent = Intent(this@LectureInfoActivity, LecturesListActivity::class.java)
        startActivity(lecturesListActivityIntent)
    }

    fun openSpeakerInfoActivity() {
        val speakerInfoActivityIntent = Intent(this@LectureInfoActivity, SpeakerInfoActivity::class.java)
        speakerInfoActivityIntent.putExtra(Speaker::class.simpleName, shishkinEvg)
        startActivity(speakerInfoActivityIntent)
    }

    fun openAuthorInfo() {
        val authorInfoActivityIntent = Intent(this@LectureInfoActivity, SpeakerInfoActivity::class.java)
        authorInfoActivityIntent.putExtra(Speaker::class.simpleName, developer)
        startActivity(authorInfoActivityIntent)
    }

}
