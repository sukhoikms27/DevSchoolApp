package com.example.lsuhinin.myapplication.pojo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lsuhinin.myapplication.R
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Speaker(
        var id: String,
        var firstName: String,
        var lastName: String,
        var location: String,
        @SerializedName(value = "jobTitle", alternate = ["job"])
        var jobTitle: String,
        var company: String,
        var about: String,
        var photo: String?,
        var flagImage: String?,
        @Embedded
        var links: Links?
) : Serializable {
    val flagResource
        get() = when (flagImage) {
            "ru" -> { R.drawable.ru_flag }
            "de" -> { R.drawable.de_flag }
            "us" -> { R.drawable.us_flag }
            "gb" -> { R.drawable.uk_flag }
            "ua" -> { R.drawable.ua_flag }
            else -> throw Exception("Flag not find: $flagImage")
        }
}

@Entity
data class Links(
        var link: String?,
        var twitter: String?,
        var telegram: String?,
        var github: String?
) : Serializable

data class Schedule(
        var talks: Collection<Talk>,
        var activities: Collection<Activity>
)

data class Talk(
        var title: String,
        var description: String,
        var room: String,
        var track: String,
        var speaker: String,
        var time: String
)

data class Activity(
        var title: String,
        var time: String
)

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

val DEVELOPER: Lecture = Lecture(
        title = "DEVELOPER",
        description = "",
        room = "",
        track = "",
        time = "",
        speaker = Speaker(
                id = "2L",
                firstName = "Leonid",
                lastName = "Sukhinin",
                jobTitle = "QA Automation",
                company = "Mail.Ru",
                location = "St. Petersburg, Russia",
                about = "QA Automation с опытом около 2 лет. Сейчас занимаюсь тестированием в проекте Юла|Авто. Есть сильное желание развиваться в android-разбработку. Пишу на Kotlin :)",
                photo = "https://pp.userapi.com/c849420/v849420980/d27fa/hxuneocDiM8.jpg?ava=1",
                flagImage = "ru",
                links = Links(
                        link = "https://github.com/sukhoikms27",
                        telegram = "https://t.me/sukhoikms27",
                        github = null,
                        twitter = null
                )
        )
)

