package com.example.lsuhinin.myapplication

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lsuhinin.myapplication.fragments.LectureDetailsFragment
import com.example.lsuhinin.myapplication.fragments.LecturesListFragment
import com.example.lsuhinin.myapplication.fragments.SpeakerDetailsFragment
import com.example.lsuhinin.myapplication.helpers.inTransaction
import com.example.lsuhinin.myapplication.pojo.Lecture

class MainActivity : AppCompatActivity(), LecturesListFragment.OnLectureSelected, LectureDetailsFragment.OnSpeakerClick, LectureDetailsFragment.OnListLecturesClick {
    override fun onListLecturesClick() {
        supportFragmentManager.popBackStack()
    }

    private val layoutResId: Int
        @LayoutRes
        get() = R.layout.fragment_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutResId)

        fun createFragment(): Fragment = LecturesListFragment.newInstance()

        var fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = createFragment()
            supportFragmentManager.inTransaction { add(R.id.fragment_container, fragment, "lecturesList") }
        }
    }

    override fun onLectureSelected(lecture: Lecture) {
        val detailsFragment = LectureDetailsFragment.newInstance(lecture)
        supportFragmentManager.inTransaction { replace(R.id.fragment_container, detailsFragment, "lectureDetails"); addToBackStack("lectureDetails") }
    }

    override fun onSpeakerClick(lecture: Lecture) {
        val speakerDetailsFragment = SpeakerDetailsFragment.newInstance(lecture)
        supportFragmentManager.inTransaction { replace(R.id.fragment_container, speakerDetailsFragment, "speakerDetails"); addToBackStack("speakerDetails") }
    }
}