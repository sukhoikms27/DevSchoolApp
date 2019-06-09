package com.example.lsuhinin.myapplication.model

import androidx.room.Embedded
import androidx.room.Entity
import com.example.lsuhinin.myapplication.R
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Speaker(
        var id: String,
        var firstName: String,
        var lastName: String,
        var location: String,
        @SerializedName(value = "jobTitle", alternate = ["job"])
        var jobTitle: String,
        var company: String,
        var about: String,
        var photo: String?,
        var flagImage: String?,
        @Embedded
        var links: Links?
) : Serializable {
    val flagResource
        get() = when (flagImage) {
            "ru" -> {
                R.drawable.ru_flag
            }
            "de" -> {
                R.drawable.de_flag
            }
            "us" -> {
                R.drawable.us_flag
            }
            "gb" -> {
                R.drawable.uk_flag
            }
            "ua" -> {
                R.drawable.ua_flag
            }
            else -> throw Exception("Flag not find: $flagImage")
        }
}