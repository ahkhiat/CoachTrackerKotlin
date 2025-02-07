package com.devid_academy.coachtrackerkotlin.data.api

import android.content.ContentValues
import android.util.Log
import com.devid_academy.coachtrackerkotlin.data.dto.auth.AuthDTO
import com.devid_academy.coachtrackerkotlin.data.dto.auth.StatusAuthDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getLogin(user: AuthDTO, onResult: (Boolean, String?) -> Unit) {

    val call: Call<StatusAuthDTO>? = ApiService.getApi().loginUser(user)
    call?.enqueue(object : Callback<StatusAuthDTO> {
        override fun onResponse(call: Call<StatusAuthDTO>, response: Response<StatusAuthDTO>) {
            Log.d(ContentValues.TAG, "Réponse du serveur : ${response.message()}")
            response.body()?.let {
                if (it.token!!.isNotEmpty()) {
                    onResult(true, it.token)
                    } else {
                        onResult(false, it.message)
                }
            }
        }
        override fun onFailure(call: Call<StatusAuthDTO>, t: Throwable) {
            Log.e(ContentValues.TAG, t?.message ?: "boo, error")
        }
    })
}