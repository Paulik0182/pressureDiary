package com.example.pressurediary.domain.entities

import android.os.Parcelable
import com.example.pressurediary.domain.Emoji
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Уневерсальный способ представление времени. Время считают (хранят) в Long
 * в data class метод copy генирируется атоматически. Пример метода ниже.
 */

@Parcelize
data class BpEntity(
    var id: Long = 0,
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

{
//    Пример метода. под капотом это выглядит так. Это полная копия объекта
//     Если сделать так самостоятельно, компилятор будет ругатся
    fun myCopy(
        id: Long = this.id,
        timeInMs: Long = this.timeInMs,
        systolicLevel: Int = this.systolicLevel,
        diastolicLevel: Int = this.diastolicLevel,
        pulse: Int = this.pulse,
        wellBeing: Emoji = this.wellBeing,
        conditionUser: String = this.conditionUser
    ): BpEntity {
        return BpEntity(id, timeInMs,systolicLevel,diastolicLevel,pulse,wellBeing,conditionUser)
    }
}