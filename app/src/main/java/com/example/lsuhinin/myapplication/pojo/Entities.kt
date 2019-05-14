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
): Serializable

data class Links(
        var link: String? = null,
        var twitter: String? = null,
        var telegram: String? = null,
        var github: String? = null
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

data class LectureObj(
        var title: String,
        var description: String,
        var room: String,
        var track: String,
        var speaker: Speaker?,
        var time: String
) : Serializable

val developer: LectureObj = LectureObj(
        title = "developer",
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
                photo = "",
                flagImage = "ru",
                links = Links(
                        link = "https://github.com/sukhoikms27",
                        telegram = "@sukhoikms27")
        )
)

