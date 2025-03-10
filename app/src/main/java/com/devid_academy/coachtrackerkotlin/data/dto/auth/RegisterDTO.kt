package com.devid_academy.coachtrackerkotlin.data.dto.auth

import com.squareup.moshi.Json

data class RegisterDTO (
    @Json(name = "email")
    val email: String,

    @Json(name = "password")
    val password: String,

    @Json(name = "firstname")
    val firstname: String,

    @Json(name = "lastname")
    val lastname: String,

    @Json(name = "birthdate")
    val birthdate: String

)