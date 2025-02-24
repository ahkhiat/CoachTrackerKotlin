package com.devid_academy.coachtrackerkotlin.data.dto
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class VisitorTeamDTO(
    val id: Int?,
    val club: ClubDTO,

    @Json(name = "age_category")
    val ageCategory: AgeCategoryDTO?

): Parcelable {
    override fun toString(): String {
        return club.name + " " + ageCategory?.name
    }
}