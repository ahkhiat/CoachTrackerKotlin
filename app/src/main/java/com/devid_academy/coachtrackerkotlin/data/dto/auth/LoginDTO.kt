package com.devid_academy.coachtrackerkotlin.data.dto.auth

import com.squareup.moshi.Json


data class LoginDTO (
    @Json(name = "email")
    val email: String,

    @Json(name = "password")
    val password: String,
)
