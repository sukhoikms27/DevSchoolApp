package com.example.lsuhinin.myapplication.model.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
