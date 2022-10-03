package com.example.pressurediary.ui.diary

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity

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

    fun bind(bpEntity: BpEntity) {
        measurements = bpEntity

        titleDataTv.text = bpEntity.data.toString()
        systolicTv.text = bpEntity.systolicLevel.toString()
        diastolicTv.text = bpEntity.diastolicLevel.toString()
        pulseTv.text = bpEntity.pulse.toString()
        wellBeingTv.text = bpEntity.wellBeing.toString()
        timeTv.text = bpEntity.time.toString()
    }

    init {
        itemView.setOnClickListener {
            measurements.let {
                listener.invoke(it)
            }
        }
    }
}