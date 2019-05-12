package com.example.lsuhinin.myapplication.network

import com.example.lsuhinin.myapplication.pojo.LectureObj
import com.example.lsuhinin.myapplication.pojo.Schedule
import com.example.lsuhinin.myapplication.pojo.Speaker
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val BASE_URL = "https://storage.yandexcloud.net/"

interface DevFestApi {

    @GET("devfestapi/data.json")
    fun getResponse(): Call<ApiResponse>


    companion object Factory {
        fun create(): DevFestApi {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(DevFestApi::class.java)
        }
    }
}

data class ApiResponse(
        var speakers: Collection<Speaker>,
        var schedule: Schedule
)

fun getLectures(response: ApiResponse): Collection<LectureObj> {
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
        )}

    return lectures
}
