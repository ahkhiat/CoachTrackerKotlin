package com.devid_academy.coachtrackerkotlin.data.dto.auth

import com.squareup.moshi.Json


data class AuthDTO (
    @Json(name = "email")
    val name: String,

    @Json(name = "password")
    val password: String,
)
