package com.example.lsuhinin.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lsuhinin.myapplication.helpers.setChipData
import com.example.lsuhinin.myapplication.pojo.Lecture
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso

class SpeakerInfoActivity : AppCompatActivity() {

    lateinit var speakerPhotoView: ImageView
    lateinit var speakerCountryView: ImageView
    lateinit var speakerNameView: TextView
    lateinit var speakerJobView: TextView
    lateinit var speakerLocationView: TextView

    lateinit var telegram: ImageView
    lateinit var link: ImageView
    lateinit var twitter: ImageView

    lateinit var speakerInfoView: TextView

    lateinit var topic: TextView
    lateinit var room: TextView
    lateinit var track: Chip
    lateinit var time: TextView

    lateinit var lectureInfo: View
    lateinit var lecture: Lecture


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.speaker_info_activity)

        speakerPhotoView = findViewById(R.id.speakerPhoto)
        speakerCountryView = findViewById(R.id.speakerCountry)
        speakerNameView = findViewById(R.id.speaker_first_name)
        speakerJobView = findViewById(R.id.speakerJob)
        speakerLocationView = findViewById(R.id.speakerLocation)
        link = findViewById(R.id.link)
        telegram = findViewById(R.id.telegram)
        twitter = findViewById(R.id.twitter)
        speakerInfoView = findViewById(R.id.speaker_info)
        topic = findViewById(R.id.topic)
        room = findViewById(R.id.room)
        track = findViewById(R.id.track)
        time = findViewById(R.id.time)
        lectureInfo = findViewById(R.id.lecture_info)

        lecture = intent.extras.getSerializable("speaker") as Lecture
        displayUserInfo(lecture)
        lectureInfo.setOnClickListener { openLectureInfoActivity() }

    }

    fun displayUserInfo(lecture: Lecture) {

        lecture.speaker?.let { speaker ->
            Picasso.get().load(speaker.photo).into(speakerPhotoView)
            speakerCountryView.setImageResource(R.drawable.rus_flag)         //FIXME прикруть поглощатор урла
            speakerNameView.text = "${speaker.firstName.toUpperCase()} ${speaker.lastName.toUpperCase()}" //FIXME разбить на два поля
            speakerJobView.text = if (speaker.company != "") { "${speaker.jobTitle} at ${speaker.company}" } else { speaker.jobTitle } //FIXME добавить в локали
            speakerLocationView.text = speaker.location
            speakerInfoView.text = speaker.about

            speaker.links?.link?.let {
                link.visibility = View.VISIBLE
                link.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(speaker.links?.link)))
                }
            }

            speaker.links?.twitter?.let {
                twitter.visibility = View.VISIBLE
                twitter.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(speaker.links?.twitter)))
                }
            }
            speaker.links?.telegram?.let {
                telegram.visibility = View.VISIBLE
                telegram.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(speaker.links?.telegram)))
                }
            }
        }

        if (lecture.title != "DEVELOPER") {
            lecture.let {
                lectureInfo.visibility = View.VISIBLE
                topic.text = lecture.title
                room.text = "Room ${lecture.room}"
                track.setChipData(lecture.track)
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
