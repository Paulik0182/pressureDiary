package com.example.pressurediary.ui.diary

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.pressurediary.R
import com.example.pressurediary.domain.Emoji
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.utils.bpTimeFormatter

private const val SYSTOLIC_MAX_KEY = 134
private const val SYSTOLIC_MIN_KEY = 114

class BpListViewHolder(
    itemView: View,
    listener: (BpEntity) -> Unit
) : BaseBpViewHolder(itemView) {

    private val systolicTv = itemView.findViewById<TextView>(R.id.systolic_text_view)
    private val diastolicTv = itemView.findViewById<TextView>(R.id.diastolic_text_view)
    private val pulseTv = itemView.findViewById<TextView>(R.id.pulse_text_view)
    private val wellBeingTv = itemView.findViewById<TextView>(R.id.well_being_text_view)
    private val timeTv = itemView.findViewById<TextView>(R.id.time_text_view)

    private lateinit var measurements: BpEntity

    @SuppressLint("NewApi")
    fun bind(bpEntity: BpEntity) {
        measurements = bpEntity

        systolicTv.text = bpEntity.systolicLevel.toString()
        diastolicTv.text = bpEntity.diastolicLevel.toString()
        pulseTv.text = bpEntity.pulse.toString()
        wellBeingTv.text = bpEntity.wellBeing.toString()
        when (bpEntity.wellBeing) {
            Emoji.FATAL -> wellBeingTv.setText(R.string.emoji_1)
            Emoji.BADLY -> wellBeingTv.setText(R.string.emoji_2)
            Emoji.FINE -> wellBeingTv.setText(R.string.emoji_3)
            Emoji.WELL -> wellBeingTv.setText(R.string.emoji_4)
            Emoji.EXCELLENT -> wellBeingTv.setText(R.string.emoji_5)
        }
        //форматируем вид представления времени. Рекомендуемый способ.
        // Уневерсальный способ представление времени. Время считают в Long
        timeTv.text = bpTimeFormatter.format(bpEntity.timeInMs)

        val systolicTvInt = systolicTv.text.toString().toInt()

        if (systolicTvInt >= SYSTOLIC_MAX_KEY) {
            systolicTv.setTextColor(Color.RED)
            diastolicTv.setTextColor(Color.RED)
            pulseTv.setTextColor(Color.RED)
            timeTv.setTextColor(Color.RED)
        } else if (systolicTvInt <= SYSTOLIC_MIN_KEY) {
            systolicTv.setTextColor(Color.MAGENTA)
            diastolicTv.setTextColor(Color.MAGENTA)
            pulseTv.setTextColor(Color.MAGENTA)
            timeTv.setTextColor(Color.MAGENTA)
        }
    }

    init {
        itemView.setOnClickListener {
            measurements.let {
                listener.invoke(it)
            }
        }
    }
}