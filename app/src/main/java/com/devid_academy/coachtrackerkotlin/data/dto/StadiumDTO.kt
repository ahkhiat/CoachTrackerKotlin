package com.devid_academy.coachtrackerkotlin.data.dto
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class StadiumDTO(
    val name: String,
    
    val adress: String?,

    @Json(name = "postal_code")
    val postalCode: String?,

    val town: String?
    
): Parcelable