package com.example.lsuhinin.myapplication.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.os.SystemClock.sleep
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lsuhinin.myapplication.R
import com.example.lsuhinin.myapplication.pojo.LectureObj
import com.example.lsuhinin.myapplication.pojo.Talk
import com.google.android.material.chip.Chip
import com.google.android.material.resources.MaterialResources.getColorStateList


class LecturesAdapter(onLectureClickListener: OnLectureClickListener) : RecyclerView.Adapter<LecturesAdapter.LectureViewHolder>() {

    private val lecturesList = ArrayList<LectureObj>()
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

    fun setItems(lectures: Collection<LectureObj>) {
        lecturesList.addAll(lectures)
        notifyDataSetChanged()
    }

    inner class LectureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val timeTextView: TextView get() = itemView.findViewById(R.id.time)
        private val topicTextView: TextView get() = itemView.findViewById(R.id.topic)
        private val roomTextView: TextView get() = itemView.findViewById(R.id.room)
        private val trackTextView: Chip get() = itemView.findViewById(R.id.track)
        private val speakerNameTextView: TextView get() = itemView.findViewById(R.id.speakerName)
        private val speakerJobInfoTextView: TextView get() = itemView.findViewById(R.id.speakerJob)

        init {
            itemView.setOnClickListener {
                onLectureClickListener.onLectureClick(lecturesList[layoutPosition])
            }
        }

        fun bind(lecture: LectureObj) {
            timeTextView.text = lecture.time
            topicTextView.text = lecture.title
            roomTextView.text = "Room ${lecture.room}"
            trackTextView.let {
                when (lecture.track) {
                    "android" -> {
                        it.text = "Android"
                        it.setChipIconResource(R.drawable.shape_oval_coral)
                    }
                    "frontend" -> {
                        it.text = "Frontend"
                        it.setChipIconResource(R.drawable.shape_oval_prismatic_blue)
                    }
                    "common" -> {
                        it.text = "Common"
                        it.setChipIconResource(R.drawable.shape_oval_violet)
                    }
                }
            }
            lecture.speaker?.let {
                            speakerNameTextView.text = "${it.firstName} ${it.lastName}" //FIXME разбить на два поля
//                            speakerCountryImageView.setImageResource(R.drawable.rus_flag) //FIXME прикруть поглощатор урла
                            speakerJobInfoTextView.text = "${it.jobTitle} at ${it.company}" //FIXME добавить в локали
//                            speakerLocationTextView.text = it.location
            }
        }
    }

    interface OnLectureClickListener {
        fun onLectureClick(lecture: LectureObj)
    }
}
