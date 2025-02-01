package com.devid_academy.coachtrackerkotlin.data.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventDTO(
    val id: Int,
    val date: String,
    val eventType: EventTypeDTO,
    val team: TeamDTO,
    val visitorTeam: VisitorTeamDTO,
    val stadium: StadiumDTO,
    val season: SeasonDTO
) : Parcelable