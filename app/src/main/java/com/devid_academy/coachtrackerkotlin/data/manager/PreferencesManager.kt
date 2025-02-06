package com.devid_academy.coachtrackerkotlin.data.manager

import android.content.Context
import com.devid_academy.coachtrackerkotlin.util.SHARED_PREFS
import com.devid_academy.coachtrackerkotlin.util.TOKEN
import com.devid_academy.coachtrackerkotlin.util.USER_ID


class PreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

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
    fun setUserId(id: Long) {
        sharedPreferences.edit().putLong(USER_ID, id).apply()
    }
}