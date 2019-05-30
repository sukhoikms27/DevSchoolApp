package com.example.lsuhinin.myapplication.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lsuhinin.myapplication.R
import com.example.lsuhinin.myapplication.helpers.setChipData
import com.example.lsuhinin.myapplication.pojo.Lecture
import kotlinx.android.synthetic.main.fragment_lecture_details.*

class LectureDetailsFragment : Fragment() {

    lateinit var lecture: Lecture
    lateinit var listenerSpeakerClick: OnSpeakerClick
    lateinit var listenerListLecturesClick: OnListLecturesClick


    companion object {

        private const val LECTURE = "lecture"

        fun newInstance(lecture: Lecture): LectureDetailsFragment {
            val args = Bundle().apply { putSerializable(LECTURE, lecture) }
            return LectureDetailsFragment().apply { arguments = args }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnSpeakerClick) {
            listenerSpeakerClick = context
        } else {
            throw ClassCastException("$context must implement OnSpeakerClick")
        }
        if (context is OnListLecturesClick) {
            listenerListLecturesClick = context
        } else {
            throw ClassCastException("$context must implement OnLecturesListClick")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lecture = arguments!!.getSerializable(LECTURE) as Lecture
        return inflater.inflate(R.layout.fragment_lecture_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayLectureInfo(lecture)

        author.setOnClickListener { listenerSpeakerClick.onSpeakerClick(lecture) }
        allLectures.setOnClickListener { listenerListLecturesClick.onListLecturesClick() }

    }

    fun displayLectureInfo(lecture: Lecture) {
        topic.text = lecture.title
        track.setChipData(lecture.track)
        author.text = "${lecture.speaker!!.firstName} ${lecture.speaker!!.lastName}" //FIXME разбить на два поля
        description.text = lecture.description
        info.visibility = View.GONE
    }

    interface OnSpeakerClick {
        fun onSpeakerClick(lecture: Lecture)
    }

    interface OnListLecturesClick {
        fun onListLecturesClick()
    }
}