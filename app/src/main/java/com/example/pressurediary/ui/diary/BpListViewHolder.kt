package com.example.pressurediary.ui.diary

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity
import java.time.format.DateTimeFormatter

class BpListViewHolder(
    itemView: View,
    listener: (BpEntity) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val titleDataTv = itemView.findViewById<TextView>(R.id.title_data_text_view)
    private val systolicTv = itemView.findViewById<TextView>(R.id.systolic_text_view)
    private val diastolicTv = itemView.findViewById<TextView>(R.id.diastolic_text_view)
    private val pulseTv = itemView.findViewById<TextView>(R.id.pulse_text_view)
    private val wellBeingTv = itemView.findViewById<TextView>(R.id.well_being_text_view)
    private val timeTv = itemView.findViewById<TextView>(R.id.time_text_view)

    private lateinit var measurements: BpEntity

    @SuppressLint("NewApi")
    fun bind(bpEntity: BpEntity) {
        measurements = bpEntity

        titleDataTv.text = bpEntity.data.format(DateTimeFormatter.ofPattern("MM.dd.yyyy"))
        systolicTv.text = bpEntity.systolicLevel.toString()
        diastolicTv.text = bpEntity.diastolicLevel.toString()
        pulseTv.text = bpEntity.pulse.toString()
        wellBeingTv.text = bpEntity.wellBeing.toString()
        //форматируем вид представления времени (это работает только на новых андройдах). Не рекомендуемый способ.
        timeTv.text = bpEntity.time.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    init {
        itemView.setOnClickListener {
            measurements.let {
                listener.invoke(it)
            }
        }
    }
}