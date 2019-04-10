package com.example.lsuhinin.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.lsuhinin.myapplication.pojo.Speaker
import com.example.lsuhinin.myapplication.pojo.shishkinEvg

class AuthorItemView : AppCompatActivity() {

    lateinit var speakerPhoto: ImageView
    lateinit var speakerCountry: ImageView
    lateinit var speakerName: TextView
    lateinit var speakerJob: TextView
    lateinit var speakerLocation: TextView

    lateinit var telegram: ImageView
    lateinit var link: ImageView
    lateinit var twitter: ImageView

    lateinit var speakerInfo: TextView

    lateinit var topic: TextView
    lateinit var room: TextView
    lateinit var track: TextView
    lateinit var time: TextView
    lateinit var date: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.author_item_view)

        speakerPhoto = findViewById(R.id.speakerPhoto)
        speakerCountry = findViewById(R.id.speakerCountry)
        speakerName = findViewById(R.id.speakerName)
        speakerJob = findViewById(R.id.speakerJob)
        speakerLocation = findViewById(R.id.speakerLocation)
        link = findViewById(R.id.link)
        telegram = findViewById(R.id.telegram)
        twitter = findViewById(R.id.twitter)
        speakerInfo = findViewById(R.id.speakerInfo)
        topic = findViewById(R.id.topic)
        room = findViewById(R.id.room)
        track = findViewById(R.id.track)
        time = findViewById(R.id.time)
        date = findViewById(R.id.date)


        link.visibility = View.GONE
        telegram.visibility = View.GONE
        twitter.visibility = View.GONE

        displayUserInfo(shishkinEvg)
    }

    fun displayUserInfo(speaker: Speaker) {

        speaker.let {
            speakerPhoto.setImageResource(it.imageSrc)
            speakerCountry.setImageResource(it.country)
            speakerName.text = it.name.toUpperCase()
            speakerJob.text = it.job
            speakerLocation.text = it.location
            speakerInfo.text = it.info
        }

        speaker.social.link?.let {
            link.visibility = View.VISIBLE
            link.setOnClickListener { Toast.makeText(this@AuthorItemView, speaker.social.link, Toast.LENGTH_SHORT).show() }
        }
        speaker.social.twitter?.let {
            twitter.visibility = View.VISIBLE
            twitter.setOnClickListener { Toast.makeText(this@AuthorItemView, speaker.social.twitter, Toast.LENGTH_SHORT).show() }
        }
        speaker.social.telegram?.let {
            telegram.visibility = View.VISIBLE
            telegram.setOnClickListener { Toast.makeText(this@AuthorItemView, speaker.social.telegram, Toast.LENGTH_SHORT).show() }
        }

        speaker.lecture.let {
            topic.text = it.topic
            room.text = it.room
            track.text = it.track
            time.text = it.time
            date.text = it.date
        }
    }
}
