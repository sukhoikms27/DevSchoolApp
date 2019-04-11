package com.example.lsuhinin.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class LecturesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lectures_list_activity)
        Toast.makeText(this@LecturesListActivity, R.string.error, Toast.LENGTH_SHORT).show()

    }
}
