package com.example.pressurediary.domain

import android.graphics.Color

enum class BpEvaluation(val color: Int) {
    NORMAL(Color.GREEN),
    PRE_HYPERTENSION(Color.BLUE),
    HYPERTENSION_1(Color.RED),
    HYPERTENSION_2(Color.CYAN),
    UNKNOWN(Color.GRAY)
}