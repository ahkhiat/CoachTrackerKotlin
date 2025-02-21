package com.devid_academy.coachtrackerkotlin.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.dto.auth.LoginDTO
import com.devid_academy.coachtrackerkotlin.databinding.FragmentLoginBinding
import com.devid_academy.coachtrackerkotlin.presentation.ui.shared.CalendarFragment
import com.devid_academy.coachtrackerkotlin.presentation.viewmodel.LoginState
import com.devid_academy.coachtrackerkotlin.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var message: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = binding.loginProgressBar
        observeLoginState()

        with(binding) {
            loginBtnLogin.setOnClickListener {
                val login = loginEtEmail.text.toString().trim()
                val password = loginEtPassword.text.toString().trim()

                if(login.isNotEmpty() && password.isNotEmpty()){
                    val user = LoginDTO(login, password)
                    viewModel.verifyLogin(user)

                } else {
                    Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                }
            }
            loginTvSignup.setOnClickListener{
                parentFragmentManager.commit {
                    replace(R.id.fg_container, RegisterFragment())
                    addToBackStack(null)
                }
            }
        }
    }

    private fun observeLoginState() {
        lifecycleScope.launch {
            viewModel.loginState.collect {
                when (it) {
                    is LoginState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is LoginState.Success -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, getString(R.string.login_successful), Toast.LENGTH_SHORT).show()

                        parentFragmentManager.commit {
                            replace(R.id.fg_container, CalendarFragment())
                        }
                    }
                    is LoginState.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show()
                    }
                    else -> LoginState.Idle
                }
            }
        }
    }
}