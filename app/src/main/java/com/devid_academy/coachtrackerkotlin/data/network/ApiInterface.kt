package com.devid_academy.coachtrackerkotlin.data.network


import com.devid_academy.coachtrackerkotlin.data.dto.EventDTO
import com.devid_academy.coachtrackerkotlin.data.dto.EventTypeDTO
import com.devid_academy.coachtrackerkotlin.data.dto.SeasonDTO
import com.devid_academy.coachtrackerkotlin.data.dto.StadiumDTO
import com.devid_academy.coachtrackerkotlin.data.dto.TeamDTO
import com.devid_academy.coachtrackerkotlin.data.dto.VisitorTeamDTO
import com.devid_academy.coachtrackerkotlin.data.dto.auth.LoginDTO
import com.devid_academy.coachtrackerkotlin.data.dto.auth.RegisterDTO
import com.devid_academy.coachtrackerkotlin.data.dto.auth.StatusAuthDTO
import com.devid_academy.coachtrackerkotlin.data.dto.response.ResponseCreateDTO
import com.devid_academy.coachtrackerkotlin.data.dto.UserProfileDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

//    Auth
    @POST(ApiRoutes.LOGIN_USER)
    suspend fun loginUser(@Body user: LoginDTO): Response<StatusAuthDTO>

    @POST(ApiRoutes.REGISTER_USER)
    suspend fun registerUser(@Body user: RegisterDTO): Response<StatusAuthDTO>

//    User
    @GET(ApiRoutes.GET_USER_PROFILE)
    suspend fun getUserProfile(): UserProfileDTO


//    Events
    @GET(ApiRoutes.GET_ALL_EVENTS)
    suspend fun getAllEvents(@Query("team.name") teamName: String): Response<List<EventDTO>>

//    @GET(ApiRoutes.GET_EVENT)
//    fun getEvent(@Path("id") eventId: Int): Call<EventDTO>
//
    @POST(ApiRoutes.ADD_EVENT)
    suspend fun insertEvent(@Body event: EventDTO): Response<ResponseCreateDTO>

    @GET(ApiRoutes.GET_EVENT_TYPES)
    suspend fun getEventTypes(): List<EventTypeDTO>


//    Teams
    @GET(ApiRoutes.GET_TEAM)
    suspend fun getTeam(@Path("id") teamId: Int): TeamDTO


//    Spinner menus
    @GET(ApiRoutes.GET_VISITOR_TEAM_LIST)
    suspend fun getVisitorTeamList(): List<VisitorTeamDTO>

    @GET(ApiRoutes.GET_STATIUM_LIST)
    suspend fun getStadiumList(): List<StadiumDTO>

    @GET(ApiRoutes.GET_SEASON_LIST)
    suspend fun getSeasonList(): List<SeasonDTO>

}