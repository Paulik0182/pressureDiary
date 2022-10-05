package com.example.pressurediary.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Уневерсальный способ представление времени. Время считают (хранят) в Long
 */

@Parcelize
data class BpEntity(
    val id: Long = 0,
    var timeInMs: Long,
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

