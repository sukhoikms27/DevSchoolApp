package com.example.lsuhinin.myapplication.helpers

import com.example.lsuhinin.myapplication.R
import com.google.android.material.chip.Chip

fun Chip.setChipData(track: String) {
    when (track) {
        "android" -> {
            this.text = "Android"
            this.setChipIconResource(R.drawable.shape_oval_coral)
        }
        "frontend" -> {
            this.text = "Frontend"
            this.setChipIconResource(R.drawable.shape_oval_prismatic_blue)
        }
        "common" -> {
            this.text = "Common"
            this.setChipIconResource(R.drawable.shape_oval_violet)
        }
    }
}

