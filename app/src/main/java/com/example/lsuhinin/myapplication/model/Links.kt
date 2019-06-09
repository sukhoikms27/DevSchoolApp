package com.example.lsuhinin.myapplication.model

import androidx.room.Entity
import java.io.Serializable

@Entity
data class Links(
        var link: String?,
        var twitter: String?,
        var telegram: String?,
        var github: String?
) : Serializable