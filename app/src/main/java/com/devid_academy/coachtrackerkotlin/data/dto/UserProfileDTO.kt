package com.devid_academy.coachtrackerkotlin.data.dto

import com.squareup.moshi.Json

data class UserProfileDTO (

    val id: Int,
    val email: String,
    val firstname: String,
    val lastname: String,
    val birthdate: String,
    val phone: String?,
    val roles: List<String>?,

    @Json(name= "plays_in")
    val playsIn: TeamDTO?,

    @Json(name= "is_coach_of")
    val isCoachOf: TeamDTO?,

    @Json(name= "is_parent_of")
    val isParentOf: List<IsParentOfDTO>?,

    )