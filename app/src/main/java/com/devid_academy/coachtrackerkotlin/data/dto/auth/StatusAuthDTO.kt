package com.devid_academy.coachtrackerkotlin.data.dto.auth

import com.squareup.moshi.Json

data class StatusAuthDTO (
    @Json(name = "code")
    val code: Int?,

    @Json(name = "message")
    val message: String?,

    @Json(name = "token")
    val token: String?
)