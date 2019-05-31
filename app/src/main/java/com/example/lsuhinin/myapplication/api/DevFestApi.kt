package com.example.lsuhinin.myapplication.api

import com.example.lsuhinin.myapplication.network.Response
import com.example.lsuhinin.myapplication.network.Retrofit
import com.example.lsuhinin.myapplication.pojo.Lecture
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET

interface DevFestApi {

    @GET("devfestapi/data.json")
    fun getResponse(): Deferred<Response>

}

suspend fun getLectures(): Collection<Lecture>? {
    val response = Retrofit.getInstance().getResponse().await()
    val talks = response.schedule.talks
    val speakers = response.speakers
    var lectures: Collection<Lecture>

    lectures = talks.map { talk ->
        Lecture(
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

