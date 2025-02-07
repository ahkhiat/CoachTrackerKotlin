package com.devid_academy.coachtrackerkotlin.data.manager

class AuthManager(private val pm: PreferencesManager ) {

    fun isAuthenticated(): Boolean {
        return pm.getToken() != null
    }

    fun logout() {
        pm.removeToken()
    }
}