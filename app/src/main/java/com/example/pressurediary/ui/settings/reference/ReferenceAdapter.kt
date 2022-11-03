package com.example.pressurediary.ui.settings.reference

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.ReferenceEntity

class ReferenceAdapter(
    private var data: List<ReferenceEntity> = mutableListOf(),
    private var openLink: (ReferenceEntity) -> Unit ={}
    ) : RecyclerView.Adapter<ReferenceViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun setData(referenceEntity: List<ReferenceEntity>){
        data = referenceEntity
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReferenceViewHolder {
        return ReferenceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_reference, parent, false),
            openLink
        )
    }

    override fun onBindViewHolder(holder: ReferenceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): ReferenceEntity = data[position]

    override fun getItemCount(): Int = data.size
}