package com.devid_academy.coachtrackerkotlin.util

import com.devid_academy.coachtrackerkotlin.R

fun getStatus(status: Int): Int {
    return when (status) {
        0 -> R.string.pending
        1 -> R.string.accepted
        2 -> R.string.rejected
        else -> R.string.status_undefined
    }
}