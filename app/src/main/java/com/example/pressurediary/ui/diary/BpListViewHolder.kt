package com.example.pressurediary.ui.diary

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.DiaryEntity

class BpListViewHolder(
    itemView: View,
    listener: (DiaryEntity) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val titleDataTv = itemView.findViewById<TextView>(R.id.title_data_text_view)
    private val upperTv = itemView.findViewById<TextView>(R.id.upper_text_view)
    private val lowerTv = itemView.findViewById<TextView>(R.id.lower_text_view)
    private val pulseTv = itemView.findViewById<TextView>(R.id.pulse_text_view)
    private val wellBeingTv = itemView.findViewById<TextView>(R.id.well_being_text_view)
    private val timeTv = itemView.findViewById<TextView>(R.id.time_text_view)

    private lateinit var measurements: DiaryEntity

    fun bind(diaryEntity: DiaryEntity) {
        measurements = diaryEntity

        titleDataTv.text = diaryEntity.data.toString()
        upperTv.text = diaryEntity.upperLevel.toString()
        lowerTv.text = diaryEntity.lowerLevel.toString()
        pulseTv.text = diaryEntity.pulse.toString()
        wellBeingTv.text = diaryEntity.wellBeing.toString()
        timeTv.text = diaryEntity.time.toString()
    }

    init {
        itemView.setOnClickListener {
            measurements.let {
                listener.invoke(it)
            }
        }
    }
}