package com.example.lsuhinin.myapplication.model.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface DevFestApi {

    @GET("devfestapi/data.json")
    fun getResponse(): Deferred<Response>

}

