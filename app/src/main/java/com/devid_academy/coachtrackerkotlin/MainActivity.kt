package com.devid_academy.coachtrackerkotlin

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devid_academy.coachtrackerkotlin.presentation.auth.LoginFragment
import com.devid_academy.coachtrackerkotlin.presentation.ui.shared.CalendarFragment
import com.devid_academy.coachtrackerkotlin.util.SHARED_PREFS
import com.devid_academy.coachtrackerkotlin.util.TOKEN

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tokenFromSp = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
            .getString(TOKEN, null)

        if(tokenFromSp.isNullOrEmpty()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fg_container, LoginFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fg_container, CalendarFragment())
                .commit()
        }

    }




}