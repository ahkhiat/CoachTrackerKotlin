package com.devid_academy.coachtrackerkotlin.data.api


import com.devid_academy.coachtrackerkotlin.data.User
import com.devid_academy.coachtrackerkotlin.data.dto.EventDTO
import com.devid_academy.coachtrackerkotlin.data.dto.auth.AuthDTO
import com.devid_academy.coachtrackerkotlin.data.dto.auth.StatusAuthDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST(ApiRoutes.LOGIN_USER)
    fun loginUser(@Body user: AuthDTO): Call<StatusAuthDTO>?

    @GET(ApiRoutes.GET_ALL_EVENTS)
    fun getAllEvents(@Query("team.name") teamName: String): Call<List<EventDTO>>


}