package com.example.lsuhinin.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var allLectures: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        allLectures = findViewById(R.id.allLectures)
        allLectures.setOnClickListener { openLecturesActivity() }
    }

    fun openLecturesActivity() {
        val lecturesListActivityIntent = Intent(this@MainActivity, LecturesListActivity::class.java)
        startActivity(lecturesListActivityIntent)
    }

}
