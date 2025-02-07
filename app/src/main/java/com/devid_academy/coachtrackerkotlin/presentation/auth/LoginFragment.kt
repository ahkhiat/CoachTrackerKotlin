package com.devid_academy.coachtrackerkotlin.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.User
import com.devid_academy.coachtrackerkotlin.data.api.getLogin
import com.devid_academy.coachtrackerkotlin.data.dto.auth.AuthDTO
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.presentation.ui.shared.CalendarFragment


class LoginFragment : Fragment() {

    private lateinit var message : String
    private lateinit var tvLogin: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        tvLogin = view.findViewById(R.id.tv_login)

        view.findViewById<Button>(R.id.fg_login_btn_login).setOnClickListener {
            val email = view.findViewById<EditText>(R.id.fg_login_email)
                .text.toString().trim()
            val password = view.findViewById<EditText>(R.id.fg_login_password)
                .text.toString().trim()

            if(email.isNotEmpty() && password.isNotEmpty()){
                verifyLogin(email, password)
            } else {
                Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
//            view.findViewById<TextView>(R.id.login_tv_signup).setOnClickListener{
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.fg_container, RegisterFragment())
//                    .addToBackStack(null)
//                    .commit()
//            }


        }

        return view
    }

    fun verifyLogin(login: String, password: String) {
        val user = AuthDTO(login, password)
        getLogin(user) { isSuccess, resultTokenOrStatus ->
            if (isSuccess) {
                message = "Connexion réussie"
                PreferencesManager(requireContext()).setToken(resultTokenOrStatus!!)
//                PreferencesManager(requireContext()).setUserId(resultId)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fg_container, CalendarFragment())
                    .commit()
            } else {
                message = resultTokenOrStatus.toString()
                }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

}
//    private fun navigateToRoleSpecificScreen(role: String) {
//        val targetFragment = when (role) {
//            "ROLE_COACH" -> CalendarFragment()
//            else -> null
//        }
//
//        if (targetFragment != null) {
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.fg_container, targetFragment)
//                .commit()
//        }
//    }

