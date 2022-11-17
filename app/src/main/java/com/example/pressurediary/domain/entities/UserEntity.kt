package com.example.pressurediary.domain.entities

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class UserEntity(

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
    ) : Parcelable