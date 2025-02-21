package com.devid_academy.coachtrackerkotlin

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.presentation.ui.shared.CalendarFragment
import com.devid_academy.coachtrackerkotlin.util.SHARED_PREFS
import com.devid_academy.coachtrackerkotlin.util.TOKEN
import com.devid_academy.coachtrackerkotlin.presentation.auth.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PreferencesManager.init(applicationContext)

        val tokenFromSp = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
            .getString(TOKEN, null)

        if(tokenFromSp.isNullOrEmpty()) {
            supportFragmentManager.commit {
                replace(R.id.fg_container, LoginFragment())
            }
        } else {
            supportFragmentManager.commit {
                replace(R.id.fg_container, CalendarFragment())
            }

        }

    }




}