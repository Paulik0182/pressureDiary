package com.example.pressurediary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DiaryEntity(
    val id: Long = 0
) : Parcelable