package com.devid_academy.coachtrackerkotlin.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.util.SHARED_PREFS
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.content.Context
import android.util.Log
import com.devid_academy.coachtrackerkotlin.util.TOKEN
import com.devid_academy.coachtrackerkotlin.util.navController


class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    fun hideLoading() {
        view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch {
            delay(3000)

            val tokenFromSp = requireContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
            .getString(TOKEN, null)
            Log.d("TOKEN", "TOKEN DANS SPLASH : $tokenFromSp")

            if(tokenFromSp.isNullOrEmpty()) {
                Log.d("NAVIGATION", "Redirection vers LoginFragment")

                navController().navigate(R.id.action_splashFragment_to_loginFragment)
            } else {
                Log.d("NAVIGATION", "Redirection vers RvCalendarFragment")

                navController().navigate(R.id.action_splashFragment_to_rvCalendarFragment)
            }

        }



    }

}