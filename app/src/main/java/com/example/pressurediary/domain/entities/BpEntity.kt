package com.example.pressurediary.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Уневерсальный способ представление времени. Время считают (хранят) в Long
 * в data class метод copy генирируется атоматически. Пример метода ниже.
 */

@Parcelize
data class BpEntity(
    var id: Long = 0,
    var timeInMs: Long = 0,
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

{
//    Пример метода. под капотом это выглядит так. Это полная копия объекта
//     Если сделать так самостоятельно, компилятор будет ругатся
    fun myCopy(
        id: Long = this.id,
        timeInMs: Long = this.timeInMs,
        systolicLevel: Int = this.systolicLevel,
        diastolicLevel: Int = this.diastolicLevel,
        pulse: Int = this.pulse,
        wellBeing: Int = this.wellBeing,
        conditionUser: String = this.conditionUser
    ): BpEntity {
        return BpEntity(id, timeInMs,systolicLevel,diastolicLevel,pulse,wellBeing,conditionUser)
    }
}