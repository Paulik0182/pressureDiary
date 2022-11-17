package com.example.pressurediary.domain.entities

import android.os.Parcelable
import com.example.pressurediary.domain.Emoji
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class BpEntity(

    var id: String = UUID.randomUUID().toString(),

    @get: PropertyName("time_in_ms")
    @field: PropertyName("time_in_ms")
    @SerializedName("time_in_ms")
    var timeInMs: Long = Calendar.getInstance().timeInMillis,

    @get: PropertyName("upper_level")
    @field: PropertyName("upper_level")
    @SerializedName("upper_level")
    var systolicLevel: Int = 120,

    @get: PropertyName("lower_level")
    @field: PropertyName("lower_level")
    @SerializedName("lower_level")
    var diastolicLevel: Int = 80,

    var pulse: Int = 60,

    @get: PropertyName("well_being")
    @field: PropertyName("well_being")
    @SerializedName("well_being")
    var wellBeing: Emoji = Emoji.FINE,

    @get: PropertyName("condition_user")
    @field: PropertyName("condition_user")
    @SerializedName("condition_user")
    var conditionUser: String = "Хорошее"

) : Parcelable