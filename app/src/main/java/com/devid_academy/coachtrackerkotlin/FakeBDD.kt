package com.devid_academy.coachtrackerkotlin

data class EventType(val id: Int, val name: String)

fun getFakeCategories(): List<EventType> {
    return listOf(
        EventType(1, "Entrainement"),
        EventType(2, "Match"),
        EventType(3, "Stage")
    )
}