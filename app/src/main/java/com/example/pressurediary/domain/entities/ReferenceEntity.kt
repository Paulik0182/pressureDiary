package com.example.pressurediary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReferenceEntity (
    var id: Long = 0,
    var name: String = "Название библиотеки",
    var referenceText: String = "Текст лицензии",
    var logoUrl: Int = 0,
    var linkAddress: String = ""
) : Parcelable