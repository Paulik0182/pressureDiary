package com.example.pressurediary.ui.diary

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.utils.bpDataFormatter
import com.example.pressurediary.utils.bpTimeFormatter
import java.time.format.DateTimeFormatter

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
        //форматируем вид представления времени. Рекомендуемый способ.
        // Уневерсальный способ представление времени. Время считают в Long
        timeTv.text = bpTimeFormatter.format(bpEntity.timeInMs)

        val systolicMax = 136
        val systolicMin = 114
        val systolicTvInt = systolicTv.text.toString().toInt()

        if (systolicTvInt >= systolicMax){
            systolicTv.setTextColor(Color.RED)
            diastolicTv.setTextColor(Color.RED)
            pulseTv.setTextColor(Color.RED)
            wellBeingTv.setTextColor(Color.RED)
            timeTv.setTextColor(Color.RED)
        } else if (systolicTvInt <= systolicMin) {
            systolicTv.setTextColor(Color.MAGENTA)
            diastolicTv.setTextColor(Color.MAGENTA)
            pulseTv.setTextColor(Color.MAGENTA)
            wellBeingTv.setTextColor(Color.MAGENTA)
            timeTv.setTextColor(Color.MAGENTA)
        }else {
            systolicTv.setTextColor(Color.GREEN)
            diastolicTv.setTextColor(Color.GREEN)
            pulseTv.setTextColor(Color.GREEN)
            wellBeingTv.setTextColor(Color.GREEN)
            timeTv.setTextColor(Color.GREEN)
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