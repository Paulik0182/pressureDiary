package com.example.pressurediary.ui.settings.reference

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.ReferenceEntity
import com.squareup.picasso.Picasso

class ReferenceViewHolder(
    itemView: View,
    openLink: (ReferenceEntity) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val nameTv = itemView.findViewById<TextView>(R.id.name_text_view)
    private val descriptionTv = itemView.findViewById<TextView>(R.id.description_text_view)
    private val logoIm = itemView.findViewById<ImageView>(R.id.logo_image_view)
    private val linkAddress = itemView.findViewById<TextView>(R.id.link_text_view)

    private lateinit var referenceEntity: ReferenceEntity

    fun bind(referenceEntity: ReferenceEntity) {
        this.referenceEntity = referenceEntity

        nameTv.text = referenceEntity.name
        descriptionTv.text = referenceEntity.referenceText
        linkAddress.text = referenceEntity.linkAddress

            Picasso.get()
                .load(referenceEntity.logoUrl)
                .into(logoIm)
            logoIm.scaleType = ImageView.ScaleType.FIT_XY// растягиваем картинку на весь элемент
    }

    init {
        linkAddress.setOnClickListener{
            referenceEntity.let {
                openLink.invoke(it)
            }
        }
    }
}