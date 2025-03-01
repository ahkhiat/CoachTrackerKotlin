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
import androidx.fragment.app.viewModels
import com.devid_academy.coachtrackerkotlin.util.TOKEN
import com.devid_academy.coachtrackerkotlin.util.navController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        viewModel.direction.observe(viewLifecycleOwner) {
            navController.navigate(it!!)
        }
    }
}