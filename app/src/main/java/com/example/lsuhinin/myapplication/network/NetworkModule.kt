package com.example.lsuhinin.myapplication.network

import com.example.lsuhinin.myapplication.api.DevFestApi
import com.example.lsuhinin.myapplication.pojo.LectureObj
import com.example.lsuhinin.myapplication.pojo.Schedule
import com.example.lsuhinin.myapplication.pojo.Speaker
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val BASE_URL = "https://storage.yandexcloud.net/"

val client = OkHttpClient.Builder()
        .build()

object Retrofit {
    fun getInstance(): DevFestApi {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(DevFestApi::class.java)

    }
}

data class Response(
        var speakers: Collection<Speaker>,
        var schedule: Schedule
)
