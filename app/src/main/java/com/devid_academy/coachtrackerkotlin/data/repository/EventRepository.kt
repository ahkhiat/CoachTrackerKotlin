package com.devid_academy.coachtrackerkotlin.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.devid_academy.coachtrackerkotlin.data.api.ApiService
import com.devid_academy.coachtrackerkotlin.data.api.MY_TEAM_NAME
import com.devid_academy.coachtrackerkotlin.data.dto.EventDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventRepository() {

    fun getEvents(onResult: (List<EventDTO>) -> Unit) {
        val call: Call<List<EventDTO>>? = ApiService.getApi().getAllEvents(MY_TEAM_NAME)
        call?.enqueue(object : Callback<List<EventDTO>> {
            override fun onResponse(call: Call<List<EventDTO>>, response: Response<List<EventDTO>>) {
                response.body()?.let {
                    onResult(it)
                }
            }
            override fun onFailure(call: Call<List<EventDTO>>, t: Throwable) {
                Log.e(TAG, t.message ?: "boo, error")
            }
        })
    }
}