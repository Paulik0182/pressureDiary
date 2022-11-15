package com.example.pressurediary.ui.utils

import android.content.Context
import android.graphics.Color
import com.example.pressurediary.R
import com.example.pressurediary.domain.BpEvaluation
import com.example.pressurediary.domain.Emoji

private val orangeColor = Color.rgb(255, 165, 0)

fun BpEvaluation.getColor(context: Context) = when (this) {
    BpEvaluation.NORMAL -> context.getColor(R.color.normal)//из ресурсов
    BpEvaluation.PRE_HYPERTENSION -> orangeColor
    BpEvaluation.HYPERTENSION_1 -> Color.rgb(255, 0, 0) //красный
    BpEvaluation.HYPERTENSION_2 -> Color.BLUE
    BpEvaluation.UNKNOWN -> Color.BLUE
}

fun Emoji.getEmoji(context: Context) = when (this) {
    Emoji.FATAL -> context.getText(R.string.emoji_1)
    Emoji.BADLY -> context.getText(R.string.emoji_2)
    Emoji.FINE -> context.getText(R.string.emoji_3)
    Emoji.WELL -> context.getText(R.string.emoji_4)
    Emoji.EXCELLENT -> context.getText(R.string.emoji_5)
}

fun Emoji.getColor(context: Context) = when (this) {
    Emoji.FATAL -> R.drawable.my_radius_emoji_fatal
    Emoji.BADLY -> R.drawable.my_radius_emoji_badly
    Emoji.FINE -> R.drawable.my_radius_emoji_fine
    Emoji.WELL -> R.drawable.my_radius_emoji_well
    Emoji.EXCELLENT -> R.drawable.my_radius_emoji_excellent
}