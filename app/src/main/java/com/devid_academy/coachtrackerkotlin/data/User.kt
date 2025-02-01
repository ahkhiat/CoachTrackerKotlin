package com.devid_academy.coachtrackerkotlin.data

data class User(val email: String, val password: String, val role: String) {

    companion object {
        val users = listOf(
            User("ahkhiat@hotmail.com", "123456", "ROLE_ADMIN"),
            User("coach@gmail.com", "123456", "ROLE_COACH"),
            User("parent@gmail.com", "123456", "ROLE_PARENT"),
            User("player@gmail.com", "123456", "ROLE_PLAYER")
        )
    }
}


