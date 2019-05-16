package com.example.lsuhinin.myapplication.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
        var links: Links
) : Serializable

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

data class Lecture(
        var title: String,
        var description: String,
        var room: String,
        var track: String,
        var speaker: Speaker?,
        var time: String
) : Serializable

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

