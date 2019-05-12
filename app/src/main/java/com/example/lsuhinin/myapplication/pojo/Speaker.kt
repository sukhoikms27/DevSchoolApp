package com.example.lsuhinin.myapplication.pojo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Speaker(
        var id: String,
        var firstName: String,
        var lastName: String,
        var location: String,
        @SerializedName("jobTitle", alternate = ["job"])
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

@Deprecated("deprecated")
data class Lecture(
        var id: Long,
        var topic: String?,
        var description: String?,
        var room: String?,
        var track: String?,
        var time: String?,
        var date: String?,
        var speaker: Speaker,
        var visibility: Boolean = true
) : Serializable

data class LectureObj(
        var title: String,
        var description: String,
        var room: String,
        var track: String,
        var speaker: Speaker?,
        var time: String
) : Serializable



val shishkinEvg: Speaker = Speaker(
        id = "1L",
        firstName = "Evgeniy",
        lastName = "Shishkin",
        location = "St. Petersburg, Russia",
        jobTitle = "Android Developer",
        company = "Aviasales",
        about = "Android-разработчик с опытом более 8 лет. Адаптировал телефоны LG под российский рынок, разрабатывал умного ассистента в i-Free и амбициозный аудиогид izi.TRAVEL. В 2016 году присоеденился к команде Aviasales, где разрабатывает приложение Hotellook для поиска лучших цен на отели. Автор популярных android open-source проектов (RobotoTextView, AppMsg, ProgressFragment, Snowfall, MultiBackStack и т.д.)",
        photo = "https://devfest-spb.com/img/speakers/EvgenyShishkin.jpg",
        flagImage = "ru",
        links = Links(
                link = "https://about.me/e.shishkin"
        )
)

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

val lectures = listOf(
        Lecture(
                id = 1L,
                topic = "Kotlin-friendly Annotation Processing",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Android",
                time = "11:15",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 2L,
                topic = "Multiplayer games with WebXR",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 2",
                track = "Frontend",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 3L,
                topic = "D8/R8 vs DX/Proguard",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Android",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 4L,
                topic = "Breaking news: A web performance story",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Frontend",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 5L,
                topic = "Поддержка офлайна в мобильном приложении. Синхронизация",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Android",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 5L,
                topic = "Delivering Fast and Beautiful Images and Video",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Frontend",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 6L,
                topic = "Как мы ускоряем Яндекс под Android",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Android",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 7L,
                topic = "There once was Swift",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Common",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 8L,
                topic = "Aviasales Отели - практический опыт объединения двух приложений",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Android",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 9L,
                topic = "Dart: второе пришествие",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Frontend",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 10L,
                topic = "Нашёл. Увидел. Запилил",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Android",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 11L,
                topic = "Большое А: от любви к ненависти и обратно",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Frontend",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        )
)
