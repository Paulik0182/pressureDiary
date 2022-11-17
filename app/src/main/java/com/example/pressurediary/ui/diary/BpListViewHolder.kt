package com.example.pressurediary.ui.diary

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.example.pressurediary.R
import com.example.pressurediary.domain.BpEvaluation
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.ui.utils.bpTimeFormatter
import com.example.pressurediary.ui.utils.getColor
import com.example.pressurediary.ui.utils.getEmoji

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
    fun bind(bpEntity: BpEntity, evaluation: BpEvaluation) {
        measurements = bpEntity

        systolicTv.text = bpEntity.systolicLevel.toString()
        diastolicTv.text = bpEntity.diastolicLevel.toString()
        pulseTv.text = bpEntity.pulse.toString()
        wellBeingTv.text = bpEntity.wellBeing.toString()

        wellBeingTv.text = bpEntity.wellBeing.getEmoji(itemView.context)

        timeTv.text = bpTimeFormatter.format(bpEntity.timeInMs)

        val color = evaluation.getColor(itemView.context)

        systolicTv.setTextColor(color)
        diastolicTv.setTextColor(color)
        pulseTv.setTextColor(color)
        timeTv.setTextColor(color)
    }

    init {
        itemView.setOnClickListener {
            measurements.let {
                listener.invoke(it)
            }
        }
    }
}