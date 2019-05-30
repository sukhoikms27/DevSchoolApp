package com.example.lsuhinin.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lsuhinin.myapplication.R
import com.example.lsuhinin.myapplication.fragments.LecturesListFragment
import com.example.lsuhinin.myapplication.helpers.setChipData
import com.example.lsuhinin.myapplication.pojo.Lecture
import com.google.android.material.chip.Chip


class LecturesAdapter(onLectureClickListener: LecturesListFragment.OnLectureSelected) : RecyclerView.Adapter<LecturesAdapter.LectureViewHolder>() {

    private val lecturesList = ArrayList<Lecture>()
    private val onLectureClickListener = onLectureClickListener

    init {
        this.onLectureClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lecture_item_view, parent, false)
        return LectureViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lecturesList.size
    }

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        holder.bind(lecturesList[position])
    }

    fun setItems(lectures: Collection<Lecture>) {
        lecturesList.addAll(lectures)
        notifyDataSetChanged()
    }

    fun getItems(): Collection<Lecture> {
        return lecturesList
    }

    inner class LectureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val timeTextView: TextView get() = itemView.findViewById(R.id.time)
        private val topicTextView: TextView get() = itemView.findViewById(R.id.topic)
        private val roomTextView: TextView get() = itemView.findViewById(R.id.room)
        private val trackTextView: Chip get() = itemView.findViewById(R.id.track)
        private val speakerNameTextView: TextView get() = itemView.findViewById(R.id.speaker_first_name)
        private val speakerJobInfoTextView: TextView get() = itemView.findViewById(R.id.speakerJob)

        init {
            itemView.setOnClickListener {
                onLectureClickListener.onLectureSelected(lecturesList[layoutPosition])
            }
        }

        fun bind(lecture: Lecture) {
            timeTextView.text = lecture.time
            topicTextView.text = lecture.title
            roomTextView.text = "Room ${lecture.room}"
            trackTextView.setChipData(lecture.track)

            lecture.speaker?.let { speaker ->
                speakerNameTextView.text = "${speaker.firstName} ${speaker.lastName}" //FIXME разбить на два поля
                speakerJobInfoTextView.text = if (speaker.company != "") {
                    "${speaker.jobTitle} at ${speaker.company}"
                } else {
                    speaker.jobTitle
                }
            }
        }
    }
}
