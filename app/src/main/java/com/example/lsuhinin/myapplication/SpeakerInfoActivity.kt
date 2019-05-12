package com.example.lsuhinin.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lsuhinin.myapplication.pojo.Lecture
import com.example.lsuhinin.myapplication.pojo.LectureObj

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
    lateinit var lecture: LectureObj


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

        lecture = intent.extras.getSerializable("speaker") as LectureObj
        displayUserInfo(lecture)
        lectureInfo.setOnClickListener { openLectureInfoActivity() }

    }

    fun displayUserInfo(lecture: LectureObj) {

        lecture.speaker?.let { speaker ->
            speakerPhoto.setImageResource(R.drawable.my_photo)         //FIXME прикруть поглощатор урла
            speakerCountry.setImageResource(R.drawable.rus_flag)         //FIXME прикруть поглощатор урла
            speakerName.text = "${speaker.firstName.toUpperCase()} ${speaker.lastName.toUpperCase()}" //FIXME разбить на два поля
            speakerJob.text = "${speaker.jobTitle} at ${speaker.company}" //FIXME добавить в локали
            speakerLocation.text = speaker.location
            speakerInfo.text = speaker.about

            speaker.links.link?.let {
                link.visibility = View.VISIBLE
                link.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(speaker.links.link)))
                }
            }
            speaker.links.twitter?.let {
                twitter.visibility = View.VISIBLE
                twitter.setOnClickListener { Toast.makeText(this@SpeakerInfoActivity, speaker.links.twitter, Toast.LENGTH_SHORT).show() }
            }
            speaker.links.telegram?.let {
                telegram.visibility = View.VISIBLE
                telegram.setOnClickListener { Toast.makeText(this@SpeakerInfoActivity, speaker.links.telegram, Toast.LENGTH_SHORT).show() }
            }
        }

        if (lecture.title != "developer") {
            lecture.let {
                lectureInfo.visibility = View.VISIBLE
                topic.text = lecture.title
                room.text = lecture.room
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
                time.text = lecture.time
            }
        }
    }

    fun openLectureInfoActivity() {
        val lectureInfoActivityIntent = Intent(this@SpeakerInfoActivity, LectureInfoActivity::class.java)
        lectureInfoActivityIntent.putExtra("lecture", lecture)
        startActivity(lectureInfoActivityIntent)
    }
}
