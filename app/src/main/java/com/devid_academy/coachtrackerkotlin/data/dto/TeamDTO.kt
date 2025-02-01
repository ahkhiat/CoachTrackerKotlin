package com.devid_academy.coachtrackerkotlin.data.dto
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamDTO (
    val name: String
): Parcelable