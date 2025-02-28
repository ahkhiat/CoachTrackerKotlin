package com.devid_academy.coachtrackerkotlin.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.devid_academy.coachtrackerkotlin.data.network.ApiService
import com.devid_academy.coachtrackerkotlin.data.dto.EventDTO
import com.devid_academy.coachtrackerkotlin.data.dto.response.ResponseCreateDTO
import com.devid_academy.coachtrackerkotlin.util.MY_TEAM_NAME
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventRepository() {

//    fun getEvents(onResult: (List<EventDTO>) -> Unit) {
//        val call: Call<List<EventDTO>>? = ApiService.getApi().getAllEvents(MY_TEAM_NAME)
//        call?.enqueue(object : Callback<List<EventDTO>> {
//            override fun onResponse(call: Call<List<EventDTO>>, response: Response<List<EventDTO>>) {
//                response.body()?.let {
//                    onResult(it)
//                }
//            }
//            override fun onFailure(call: Call<List<EventDTO>>, t: Throwable) {
//                Log.e(TAG, t.message ?: "boo, error")
//            }
//        })
//    }
//
//    fun getEvent(eventId: Int, onResult: (EventDTO) -> Unit) {
//        val call: Call<EventDTO>? = ApiService.getApi().getEvent(eventId)
//        call?.enqueue(object : Callback<EventDTO> {
//            override fun onResponse(call: Call<EventDTO>, response: Response<EventDTO>) {
//                response.body()?.let {
//                    onResult(it)
//                }
//            }
//            override fun onFailure(call: Call<EventDTO>, t: Throwable) {
//                Log.e(TAG, t.message ?: "boo, error")
//            }
//        })
//    }
//
//    fun getAddEvent(event: EventDTO, onResult: (Boolean) -> Unit) {
//        val call: Call<ResponseCreateDTO>? = ApiService.getApi().insertEvent(event)
//        call?.enqueue(object : Callback<ResponseCreateDTO> {
//            override fun onResponse(call: Call<ResponseCreateDTO>, response: Response<ResponseCreateDTO>) {
//                Log.d(TAG, "Réponse du serveur : ${response.message()}")
//                response.body()?.let {
//                    onResult(true)
//                }
//            }
//            override fun onFailure(call: Call<ResponseCreateDTO>, t: Throwable) {
//                Log.e(TAG, t?.message ?: "boo, error")
//            }
//        })
//    }
//
//    private val api = ApiService.getApi()

//    suspend fun getEventTypes(): List<EventTypeDTO> {
//        return try {
//            val response = api.getEventTypes() ?: emptyList()
//            Log.i("API CALL", "REPO CATEGORIES : $response")
//            response
//        } catch (e: Exception) {
//            Log.e("API CALL", "Erreur : ${e.message}", e)
//            emptyList()
//        }
//    }

}