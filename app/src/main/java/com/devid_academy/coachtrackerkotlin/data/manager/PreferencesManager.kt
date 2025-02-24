package com.devid_academy.coachtrackerkotlin.data.manager

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.auth0.android.jwt.DecodeException
import com.auth0.android.jwt.JWT
import com.devid_academy.coachtrackerkotlin.data.dto.UserProfileDTO
import com.devid_academy.coachtrackerkotlin.util.SHARED_PREFS
import com.devid_academy.coachtrackerkotlin.util.TOKEN
import com.devid_academy.coachtrackerkotlin.util.USER_ID
import com.devid_academy.coachtrackerkotlin.util.USER_KEY
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


object PreferencesManager {

    private lateinit var sharedPreferences: SharedPreferences
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val adapter = moshi.adapter(UserProfileDTO::class.java)

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
    }
    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, null)
    }
    fun setToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }
    fun removeToken() {
        sharedPreferences.edit().remove(TOKEN).apply()
    }
    fun getUserId(): Long {
        return sharedPreferences.getLong(USER_ID, -1L)
    }
    fun setUserId() {
        sharedPreferences.edit().putLong(USER_ID, 15).apply()
        Log.i("USER","USER ID : ${getUserId()}" )
    }

    fun saveUser(user: UserProfileDTO) {
        val userJson = adapter.toJson(user)
        sharedPreferences.edit().putString(USER_KEY, userJson).apply()
    }

    fun getUser(): UserProfileDTO? {
        val userJson = sharedPreferences.getString(USER_KEY, null) ?: return null
        return adapter.fromJson(userJson)
    }

    fun clearUser() {
        sharedPreferences.edit().remove(USER_KEY).apply()
    }


    fun getUsernameFromToken(): String? {
        val token = sharedPreferences.getString(TOKEN, null)
        if (token.isNullOrEmpty()) {
            return null
        }

        return try {
            val jwt = JWT(token)
            jwt.getClaim("username").asString()
        } catch (exception: DecodeException) {
            null
        }
    }

}