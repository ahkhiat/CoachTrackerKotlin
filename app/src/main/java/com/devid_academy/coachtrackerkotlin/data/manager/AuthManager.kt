package com.devid_academy.coachtrackerkotlin.data.manager

object AuthManager {

    fun logout() {
        PreferencesManager.removeToken()
    }
}