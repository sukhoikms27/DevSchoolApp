package com.example.lsuhinin.myapplication.adapter

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lsuhinin.myapplication.R
import com.example.lsuhinin.myapplication.pojo.Lecture


class LecturesAdapter(onLectureClickListener: OnLectureClickListener) : RecyclerView.Adapter<LecturesAdapter.LectureViewHolder>() {

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

    inner class LectureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val timeTextView: TextView get() = itemView.findViewById(R.id.time)
        private val topicTextView: TextView get() = itemView.findViewById(R.id.topic)
        private val roomTextView: TextView get() = itemView.findViewById(R.id.room)
        private val trackTextView: TextView get() = itemView.findViewById(R.id.track)
        private val speakerCountryImageView: ImageView get() = itemView.findViewById(R.id.speakerCountry)
        private val speakerNameTextView: TextView get() = itemView.findViewById(R.id.speakerName)
        private val speakerJobInfoTextView: TextView get() = itemView.findViewById(R.id.speakerJob)
        private val speakerLocationTextView: TextView get() = itemView.findViewById(R.id.speakerLocation)

        init {
            itemView.setOnClickListener {
                onLectureClickListener.onLectureClick(lecturesList[layoutPosition])
            }
        }

        fun bind(lecture: Lecture) {
            timeTextView.text = lecture.time
            topicTextView.text = lecture.topic
            roomTextView.text = lecture.room
            trackTextView.let {
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
            lecture.speaker.let {
                speakerNameTextView.text = it.name
                speakerCountryImageView.setImageResource(it.country)
                speakerJobInfoTextView.text = it.job
                speakerLocationTextView.text = it.location
            }

        }
    }

    interface OnLectureClickListener {
        fun onLectureClick(lecture: Lecture)
    }
}
