package com.example.lsuhinin.myapplication.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lsuhinin.myapplication.R
import com.example.lsuhinin.myapplication.pojo.Lecture


class LecturesAdapter(onLectureClickListener: OnLectureClickListener): RecyclerView.Adapter<LecturesAdapter.LectureViewHolder>() {

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

        private val topicTextView: TextView get() = itemView.findViewById(R.id.topic)

        init {
            itemView.setOnClickListener {
                onLectureClickListener.onLectureClick(lecturesList[layoutPosition])
            }
        }

        fun bind(lecture: Lecture) {
            topicTextView.text = lecture.topic
        }
    }

    interface OnLectureClickListener {
        fun onLectureClick(lecture: Lecture)
    }
}
