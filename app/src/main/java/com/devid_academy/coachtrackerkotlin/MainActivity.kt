package com.devid_academy.coachtrackerkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devid_academy.coachtrackerkotlin.presentation.auth.LoginFragment
import com.devid_academy.coachtrackerkotlin.presentation.ui.shared.CalendarFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fragmentLogin = LoginFragment()
        val calendarFragment = CalendarFragment()

        supportFragmentManager.beginTransaction()
//            .replace(R.id.fg_container, fragmentLogin)
            .replace(R.id.fg_container, calendarFragment)
            .commit()

    }




}