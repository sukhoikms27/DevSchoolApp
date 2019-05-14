package com.example.lsuhinin.myapplication.api

import com.example.lsuhinin.myapplication.network.Response
import com.example.lsuhinin.myapplication.pojo.LectureObj
import retrofit2.Call
import retrofit2.http.GET

interface DevFestApi {

    @GET("devfestapi/data.json")
    fun getResponse(): Call<Response>

}

fun getLectures(response: Response): Collection<LectureObj>? {
    val talks = response.schedule.talks
    val speakers = response.speakers
    var lectures: Collection<LectureObj>

    lectures = talks.map { talk ->
        LectureObj(
                title = talk.title,
                description = talk.description,
                room = talk.room,
                track = talk.track,
                speaker = speakers.find { it.id == talk.speaker },
                time = talk.time
        )
    }

    return lectures
}

