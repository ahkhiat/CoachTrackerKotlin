package com.devid_academy.coachtrackerkotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fragmentLogin = LoginFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fg_container, fragmentLogin)
            .commit()

    }
}