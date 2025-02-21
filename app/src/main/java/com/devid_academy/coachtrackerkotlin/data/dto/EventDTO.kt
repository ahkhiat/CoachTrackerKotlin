package com.devid_academy.coachtrackerkotlin.data.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventDTO(

    val id: Int,
    val date: String,

    @Json(name = "event_type")
    val eventType: EventTypeDTO,

    val team: TeamDTO,

    @Json(name = "visitor_team")
    val visitorTeam: VisitorTeamDTO?,

    val stadium: StadiumDTO,
    val season: SeasonDTO,
    val presences: List<PresenceDTO>?,
    val convocations: List<ConvocationDTO>?

) : Parcelable