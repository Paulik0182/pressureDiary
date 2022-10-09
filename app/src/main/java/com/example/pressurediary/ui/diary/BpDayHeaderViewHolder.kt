package com.example.pressurediary.ui.diary

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.example.pressurediary.R
import com.example.pressurediary.utils.bpDataFormatter

/**
 * Это заголовок дня показаний давлений
 */

class BpDayHeaderViewHolder(
    itemView: View,
) : BaseBpViewHolder(itemView) {

    private val titleHeaderTv = itemView.findViewById<TextView>(R.id.title_data_text_view)

    @SuppressLint("NewApi")
    fun bind(dey: Long) {
        titleHeaderTv.text = bpDataFormatter.format(dey)
    }
}