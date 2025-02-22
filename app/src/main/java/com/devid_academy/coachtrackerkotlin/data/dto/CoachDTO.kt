package com.devid_academy.coachtrackerkotlin.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class CoachDTO (

    val id: Int?,
    val user: UserDTO?
): Parcelable