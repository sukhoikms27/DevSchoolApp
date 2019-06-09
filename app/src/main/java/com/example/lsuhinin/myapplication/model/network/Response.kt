package com.example.lsuhinin.myapplication.model.network

import com.example.lsuhinin.myapplication.model.Speaker

data class Response(
        var speakers: Collection<Speaker>,
        var schedule: Schedule
)

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