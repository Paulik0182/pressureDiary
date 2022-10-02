package com.example.pressurediary.ui.diary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.DiaryEntity

class BpListAdapter(
    private var data: List<DiaryEntity>,
    private var listener: (DiaryEntity) -> Unit
) : RecyclerView.Adapter<BpListViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cardio: List<DiaryEntity>){
        data = cardio
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (DiaryEntity) -> Unit){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BpListViewHolder {
        return BpListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cardio, parent, false), listener
        )
    }

    override fun onBindViewHolder(holder: BpListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): DiaryEntity = data[position]

    override fun getItemCount(): Int = data.size
}