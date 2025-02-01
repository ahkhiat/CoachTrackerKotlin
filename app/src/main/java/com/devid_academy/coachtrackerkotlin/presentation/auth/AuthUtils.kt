package com.devid_academy.coachtrackerkotlin.presentation.auth

import com.devid_academy.coachtrackerkotlin.data.User

fun validateLogin(email: String, password: String, users: List<User>): User? {
    return users.find { user -> user.email == email && user.password == password }
}
