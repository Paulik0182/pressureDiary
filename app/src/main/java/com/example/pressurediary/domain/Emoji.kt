package com.example.pressurediary.domain

import android.graphics.Color

enum class Emoji(val color: Int)  {
    FATAL(Color.RED),
    BADLY(Color.RED),
    FINE(Color.YELLOW),
    WELL(Color.GREEN),
    EXCELLENT(Color.GREEN)
}