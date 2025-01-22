package com.devid_academy.coachtrackerkotlin

fun validateLogin(email: String, password: String, users: List<User>): User? {
    return users.find { user -> user.email == email && user.password == password }
}
