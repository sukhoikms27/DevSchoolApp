package com.example.lsuhinin.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class LecturesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lectures_list)
        Toast.makeText(this@LecturesListActivity,"Sorry! Maybe next time", Toast.LENGTH_SHORT).show()

    }
}
