package com.devid_academy.coachtrackerkotlin.data.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class PresenceDTO (

    val id: Int,

    @Json(name = "on_time")
    val onTime : String,

    val player : PlayerDTO
) : Parcelable