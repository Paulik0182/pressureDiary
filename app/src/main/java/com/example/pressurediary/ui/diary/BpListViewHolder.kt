package com.example.pressurediary.ui.diary

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.example.pressurediary.R
import com.example.pressurediary.domain.BpEvaluation
import com.example.pressurediary.domain.Emoji
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

//        val emoji1 = emoji.getEmoji(itemView.context)
//        wellBeingTv.setText(emoji1)
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

        //вариант 3. раскрашиваем цветами
        val color = evaluation.getColor(itemView.context)

        //вариант 2. раскрашиваем цветами
//        val color = MappersExtensions.getColor(itemView.context, evaluation)

        //вариант 1.
//        val color = when (evaluation) {
//            BpEvaluation.NORMAL -> BpEvaluation.NORMAL.color
//            BpEvaluation.PRE_HYPERTENSION -> BpEvaluation.PRE_HYPERTENSION.color
//            BpEvaluation.HYPERTENSION_1 -> BpEvaluation.HYPERTENSION_1.color
//            BpEvaluation.HYPERTENSION_2 -> BpEvaluation.HYPERTENSION_2.color
//            BpEvaluation.UNKNOWN -> BpEvaluation.UNKNOWN.color
//        }

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