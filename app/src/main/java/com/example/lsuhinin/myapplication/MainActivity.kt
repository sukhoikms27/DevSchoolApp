package com.example.lsuhinin.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var allLectures: Button
    lateinit var author: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        allLectures = findViewById(R.id.allLectures)
        author = findViewById(R.id.author)

        allLectures.setOnClickListener { openLecturesActivity() }
        author.setOnClickListener { openAuthorInfoActivity() }
    }

    fun openLecturesActivity() {
        val lecturesListActivityIntent = Intent(this@MainActivity, LecturesListActivity::class.java)
        startActivity(lecturesListActivityIntent)
    }

    fun openAuthorInfoActivity() {
        val authorInfoActivityIntent = Intent(this@MainActivity, AuthorItemView::class.java)
        startActivity(authorInfoActivityIntent)
    }

}
