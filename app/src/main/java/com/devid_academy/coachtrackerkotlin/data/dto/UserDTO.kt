package com.devid_academy.coachtrackerkotlin.data.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDTO (

    @Json(name = "id")
    val id: Int?,

    val firstname : String,

    val lastname: String?
) : Parcelable