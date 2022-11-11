package com.example.pressurediary.domain.entities

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Уневерсальный способ представление времени. Время считают (хранят) в Long
 * в data class метод copy генирируется атоматически. Пример метода ниже.
 */

@Parcelize
data class UserEntity(

//случайный набор символов (наверное в 16ой системе. 1 к триллиону вероятность коллизии)
    @get: PropertyName("id")
    @field: PropertyName("id")
    @SerializedName("id")
    var id: String = UUID.randomUUID().toString(),

    @get: PropertyName("creation_date_in_ms")
    @field: PropertyName("creation_date_in_ms")
    @SerializedName("creation_date_in_ms")
    var creationDateInMs: Long = Calendar.getInstance().timeInMillis,

    @get: PropertyName("name")
    @field: PropertyName("name")
    @SerializedName("name")
    var name: String = "no name",

    @get: PropertyName("email")
    @field: PropertyName("email")
    @SerializedName("email")
    var email: String = "",

    @get: PropertyName("password")
    @field: PropertyName("password")
    @SerializedName("password")
    var password: Int = 0

    ) : Parcelable