package com.example.lsuhinin.myapplication.network

import com.example.lsuhinin.myapplication.api.DevFestApi
import com.example.lsuhinin.myapplication.pojo.Schedule
import com.example.lsuhinin.myapplication.pojo.Speaker
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://storage.yandexcloud.net/"

object Retrofit {
    fun getInstance(): DevFestApi {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(DevFestApi::class.java)

    }
}

data class Response(
        var speakers: Collection<Speaker>,
        var schedule: Schedule
)
