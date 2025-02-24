package com.devid_academy.coachtrackerkotlin.data.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class IsParentOfDTO (

    @Json(name = "user_id")
    val userId: UserDTO,

    @Json(name= "child_id")
    val childId: UserDTO

): Parcelable
