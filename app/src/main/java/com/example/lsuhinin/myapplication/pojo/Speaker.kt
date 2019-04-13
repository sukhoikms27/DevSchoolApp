package com.example.lsuhinin.myapplication.pojo

import com.example.lsuhinin.myapplication.R
import java.io.Serializable

data class Speaker(
        var id: Long,
        var imageSrc: Int,
        var country: Int,
        var name: String,
        var job: String,
        var location: String,
        var info: String,
        var social: Social
) : Serializable

data class Social(
        var link: String?,
        var twitter: String?,
        var telegram: String?
) : Serializable

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

val shishkinEvg: Speaker = Speaker(
        id = 1L,
        imageSrc = R.drawable.author,
        country = R.drawable.rus_flag,
        name = "Evgeny Shishkin",
        job = "Android Developer at Aviasales",
        location = "St. Petersburg, Russia",
        info = "Android-разработчик с опытом более 8 лет. Адаптировал телефоны LG под российский рынок, разрабатывал умного ассистента в i-Free и амбициозный аудиогид izi.TRAVEL. В 2016 году присоеденился к команде Aviasales, где разрабатывает приложение Hotellook для поиска лучших цен на отели. Автор популярных android open-source проектов (RobotoTextView, AppMsg, ProgressFragment, Snowfall, MultiBackStack и т.д.)",
        social = Social(
                link = "https://about.me/e.shishkin",
                twitter = null,
                telegram = null)
)

val developer: Lecture = Lecture(
        id = 999L,
        topic = null,
        description = null,
        room = null,
        track = null,
        time = null,
        date = null,
        visibility = false,
        speaker = Speaker(
                id = 2L,
                imageSrc = R.drawable.my_photo,
                country = R.drawable.rus_flag,
                name = "Leonid Sukhinin",
                job = "QA Automation in Mail.Ru",
                location = "St. Petersburg, Russia",
                info = "QA Automation с опытом около 2 лет. Сейчас занимаюсь тестированием в проекте Юла|Авто. Есть сильное желание развиваться в android-разбработку. Пишу на Kotlin :)",
                social = Social(
                        link = "https://github.com/sukhoikms27",
                        twitter = null,
                        telegram = "@sukhoikms27")
        )
)

val lectures = listOf(
        Lecture(
                id = 1L,
                topic = "Aviasales Отели Remastered",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Android",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 2L,
                topic = "Aviasales Отели: First Encounter",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Android",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 3L,
                topic = "Aviasales Отели: Second Encounter",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Android",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 4L,
                topic = "Aviasales Отели 2",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Android",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        ),
        Lecture(
                id = 5L,
                topic = "Aviasales Отели 3: BFE",
                description = "До недавнего времени у нас независимо существовало два приложения: Aviasales — метапоиск авиабилетов и Hotellook — метапоиск отелей/хостелов/аппартов. И все было хорошо, пока не появилась задача интегрировать возможность поиска и бронирования отелей в приложение Aviasales, для того чтобы увеличить конверсию пользователей в отельную часть и зарабатывать еще больше денег \uD83D\uDE01 Так как Aviasales и Hotellook — это совершенно разные проекты с разными командами, разными архитектурными подходами, задача эта оказалась весьма непростой и интересной. Спойлер: В результате объединения приложения свет увидели 2 любопытные open source библиотеки: mrButler и nautilus.",
                room = "Room 1",
                track = "Android",
                time = "16:35",
                date = "27 November",
                speaker = shishkinEvg
        )



)
