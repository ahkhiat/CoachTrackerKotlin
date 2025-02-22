package com.devid_academy.coachtrackerkotlin.presentation.ui.shared.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.manager.AuthManager
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.databinding.FragmentProfileBinding
import com.devid_academy.coachtrackerkotlin.util.navController


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("DEBUG", "btnLogout: $binding.btnLogout")

        binding.btnLogout.setOnClickListener {
            Toast.makeText(requireContext(), "Déconnexion...", Toast.LENGTH_SHORT).show()
            Log.d("TOKEN", "TOKEN AVANT LOGOUT : ${PreferencesManager.getToken()}")
            AuthManager.logout()
            Log.d("TOKEN", "TOKEN APRES LOGOUT : ${PreferencesManager.getToken()}")

            navController().navigate(R.id.action_profileFragment_to_splashFragment)
        }


    }

}