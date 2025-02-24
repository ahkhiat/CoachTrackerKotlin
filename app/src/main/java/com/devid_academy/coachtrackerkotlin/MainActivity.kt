package com.devid_academy.coachtrackerkotlin

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.databinding.ActivityMainBinding
import com.devid_academy.coachtrackerkotlin.presentation.ui.shared.rvcalendar.RvCalendarFragment
import com.devid_academy.coachtrackerkotlin.util.SHARED_PREFS
import com.devid_academy.coachtrackerkotlin.util.TOKEN
import com.devid_academy.coachtrackerkotlin.presentation.auth.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fg_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        val fragmentsWithoutBottomBar = listOf(
            R.id.splashFragment,
            R.id.loginFragment,
            R.id.registerFragment
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigationView.visibility =
                if (destination.id in fragmentsWithoutBottomBar) View.GONE else View.VISIBLE
        }


        PreferencesManager.init(applicationContext)



    }





}