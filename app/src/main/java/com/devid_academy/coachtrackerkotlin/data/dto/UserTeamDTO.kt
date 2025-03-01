package com.devid_academy.coachtrackerkotlin.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserTeamDTO (

    val id: Int?,
    val name: String?,

): Parcelable