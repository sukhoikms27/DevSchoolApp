package com.example.lsuhinin.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.lsuhinin.myapplication.pojo.Lecture

class SpeakerInfoActivity : AppCompatActivity() {

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

    lateinit var lectureInfo: View
    lateinit var lecture: Lecture



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.speaker_info_activity)

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
        lectureInfo = findViewById(R.id.lecture_info)

        lecture = intent.extras.getSerializable("speaker") as Lecture
        displayUserInfo(lecture)
        lectureInfo.setOnClickListener { openLectureInfoActivity() }

    }

    fun displayUserInfo(lecture: Lecture) {

        lecture.speaker.let {
            speakerPhoto.setImageResource(it.imageSrc)
            speakerCountry.setImageResource(it.country)
            speakerName.text = it.name.toUpperCase()
            speakerJob.text = it.job
            speakerLocation.text = it.location
            speakerInfo.text = it.info
        }

        lecture.speaker.social.link?.let {
            link.visibility = View.VISIBLE
            link.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(lecture.speaker.social.link)))
            }
        }
        lecture.speaker.social.twitter?.let {
            twitter.visibility = View.VISIBLE
            twitter.setOnClickListener { Toast.makeText(this@SpeakerInfoActivity, lecture.speaker.social.twitter, Toast.LENGTH_SHORT).show() }
        }
        lecture.speaker.social.telegram?.let {
            telegram.visibility = View.VISIBLE
            telegram.setOnClickListener { Toast.makeText(this@SpeakerInfoActivity, lecture.speaker.social.telegram, Toast.LENGTH_SHORT).show() }
        }

        if (lecture.visibility) {
            lecture.let {
                lectureInfo.visibility = View.VISIBLE
                topic.text = lecture.topic
                room.text = lecture.room
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
                time.text = lecture.time
                date.text = lecture.date
            }
        }
    }

    fun openLectureInfoActivity() {
        val lectureInfoActivityIntent = Intent(this@SpeakerInfoActivity, LectureInfoActivity::class.java)
        lectureInfoActivityIntent.putExtra(LectureInfoActivity().LECTURE_ID, lecture.id)
        startActivity(lectureInfoActivityIntent)
    }
}
