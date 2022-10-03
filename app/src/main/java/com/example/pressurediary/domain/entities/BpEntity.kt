package com.example.pressurediary.domain.entities

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

    @RequiresApi(Build.VERSION_CODES.O)
    var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

@Parcelize
data class BpEntity(
    val id: Long = 0,
    var data: LocalDate,
    var time: LocalTime,
    @SerializedName("upper_level")
    var systolicLevel: Int = 120,
    @SerializedName("lower_level")
    var diastolicLevel: Int = 80,
    var pulse: Int = 60,
    @SerializedName("well_being")
    var wellBeing: Int = 3,
    @SerializedName("condition_user")
    var conditionUser: String = "Хорошее"
) : Parcelable

