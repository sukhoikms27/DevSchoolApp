package com.example.lsuhinin.myapplication.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lsuhinin.myapplication.R
import com.example.lsuhinin.myapplication.pojo.Lecture
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_speaker_details.*

class SpeakerDetailsFragment : Fragment() {
    lateinit var lecture: Lecture

    companion object {

        private const val LECTURE = "lecture"

        fun newInstance(lecture: Lecture): SpeakerDetailsFragment {
            return SpeakerDetailsFragment().apply { arguments = Bundle().apply { putSerializable(LECTURE, lecture) } }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lecture = arguments!!.getSerializable(LECTURE) as Lecture
        return inflater.inflate(R.layout.fragment_speaker_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayUserInfo(lecture)
    }

    fun displayUserInfo(lecture: Lecture) {
        lecture.speaker?.let { speaker ->
            Picasso.get().load(speaker.photo).into(speakerPhoto)
            speakerCountry.setImageResource(speaker.flagResource)         //FIXME прикруть поглощатор урла
            speaker_first_name.text = "${speaker.firstName.capitalize()} ${speaker.lastName.capitalize()}" //FIXME разбить на два поля
            speakerJob.text = if (speaker.company != "") {
                "${speaker.jobTitle} at ${speaker.company}"
            } else {
                speaker.jobTitle
            }
            speakerLocation.text = speaker.location
            speaker_info.text = speaker.about

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
                lecture_info.visibility = View.VISIBLE
                topic.text = lecture.title
                room.text = lecture.roomText
                track.text = lecture.trackText
                track.setChipIconResource(lecture.trackIcon)
                time.text = lecture.time
            }
        }


    }
}