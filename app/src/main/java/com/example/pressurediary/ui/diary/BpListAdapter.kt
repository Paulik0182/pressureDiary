package com.example.pressurediary.ui.diary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity

class BpListAdapter(
    private var data: List<BpEntity>,
    private var listener: (BpEntity) -> Unit
) : RecyclerView.Adapter<BpListViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<BpEntity>){
        this.data = data.sortedByDescending { it.timeInMs }// Делаем обратную сортировку данных на конкретном экране
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (BpEntity) -> Unit){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BpListViewHolder {
        return BpListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bp, parent, false), listener
        )
    }

    override fun onBindViewHolder(holder: BpListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): BpEntity = data[position]

    override fun getItemCount(): Int = data.size
}