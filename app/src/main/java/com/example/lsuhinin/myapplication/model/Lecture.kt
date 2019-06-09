package com.example.lsuhinin.myapplication.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lsuhinin.myapplication.R
import com.example.lsuhinin.myapplication.model.network.Response
import java.io.Serializable

@Entity
data class Lecture(
        @PrimaryKey(autoGenerate = true)
        var LectureId: Int? = null,
        var title: String,
        var description: String,
        var room: String,
        var track: String,
        @Embedded
        var speaker: Speaker?,
        var time: String
) : Serializable {

    val roomText get() = "Room $room"

    val trackText
        get() = track.capitalize()

    val trackIcon
        get() = when (track) {
            "android" -> {
                R.drawable.shape_oval_coral
            }
            "frontend" -> {
                R.drawable.shape_oval_prismatic_blue
            }
            "common" -> {
                R.drawable.shape_oval_violet
            }
            else -> throw Exception("Track not find: $track")
        }

}

fun getLectures(response: Response): Collection<Lecture> {
    val talks = response.schedule.talks
    val speakers = response.speakers

    return talks.map { talk ->
        Lecture(
                title = talk.title,
                description = talk.description,
                room = talk.room,
                track = talk.track,
                speaker = speakers.find { it.id == talk.speaker },
                time = talk.time
        )
    }
}