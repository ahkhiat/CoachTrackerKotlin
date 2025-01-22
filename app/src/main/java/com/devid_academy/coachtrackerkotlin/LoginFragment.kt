package com.devid_academy.coachtrackerkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.logging.Handler


class LoginFragment : Fragment() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var tvLogin: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        emailInput = view.findViewById(R.id.fg_login_email)
        passwordInput = view.findViewById(R.id.fg_login_password)
        loginButton = view.findViewById(R.id.fg_login_btn_login)
        tvLogin = view.findViewById(R.id.tv_login)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            val user = validateLogin(email, password, User.users)

            if(user != null) {
                tvLogin.text = "Bienvenue"
                navigateToRoleSpecificScreen(user.role)
            } else {
                tvLogin.text = "Nom d\'utilisateur ou mot de passe incorrect"
            }

        }

        return view
    }

    private fun navigateToRoleSpecificScreen(role: String) {
        val targetFragment = when (role) {
            "ROLE_COACH" -> CoachFragmentHome()
            "ROLE_PLAYER" -> PlayerFragmentHome()
            else -> null
        }

        if (targetFragment != null) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fg_container, targetFragment)
                .commit()
        }
    }




}