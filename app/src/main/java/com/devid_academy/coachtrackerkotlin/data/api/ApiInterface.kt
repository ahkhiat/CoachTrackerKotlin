package com.devid_academy.coachtrackerkotlin.data.api


import com.devid_academy.coachtrackerkotlin.data.dto.EventDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @GET(ApiRoutes.GET_ALL_EVENTS)
    fun getAllEvents(@Query("team.name") teamName: String): Call<List<EventDTO>>
//    fun getAllEvents(): Call<List<EventDTO>>

//    @FormUrlEncoded // pour gérer les accents
//    @POST(ApiRoutes.ADD_COUNTRY)
//    fun insertCountry(
//        @Field("nom") countryName: String,
//        @Field("url") imageUrl : String,
//        @Field("id_s") stagiaireId: Long
//    ): Call<RetourDTO>
//
//    @GET(ApiRoutes.GET_COUNTRY)
//    fun getCountry(@Query("id") countryId: Long): Call<CountryDTO>?
//
//    @POST(ApiRoutes.UPDATE_COUNTRY)
//    fun updateCountry(@Body updateCountry: UpdateCountryDTO): Call<RetourDTO>?
//
//    @FormUrlEncoded
//    @POST(ApiRoutes.DELETE_COUNTRY)
//    fun deleteCountry(@Field("id") countryId: Long): Call<RetourDTO>?

}