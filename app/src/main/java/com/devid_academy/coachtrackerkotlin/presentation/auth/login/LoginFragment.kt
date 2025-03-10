package com.devid_academy.coachtrackerkotlin.presentation.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.databinding.FragmentLoginBinding
import com.devid_academy.coachtrackerkotlin.presentation.viewmodel.LoginState
import com.devid_academy.coachtrackerkotlin.presentation.viewmodel.LoginViewModel
import com.devid_academy.coachtrackerkotlin.util.navController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    @Inject
    lateinit var preferencesManager: PreferencesManager

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var progressBar: ProgressBar

    private lateinit var emailForm: String
    private lateinit var passwordForm: String

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

        viewModel.resetLoginState()
        observeLoginState()

        with(binding) {

            loginBtnLogin.setOnClickListener {
                emailForm = loginEtEmail.text.toString().trim()
                passwordForm = loginEtPassword.text.toString().trim()
                viewModel.verifyLogin(emailForm, passwordForm)
            }
            loginTvSignup.setOnClickListener{
                navController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }
    private fun observeLoginState() {
        viewModel.loginState.observe(viewLifecycleOwner) {
            when (it) {
                is LoginState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is LoginState.Incomplete -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(context, getString(R.string.fill_all_inputs), Toast.LENGTH_SHORT).show()
                }
                is LoginState.Success -> {
                    if(!(preferencesManager.getToken()).isNullOrEmpty()) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, getString(R.string.login_successful), Toast.LENGTH_SHORT).show()
                        navController().navigate(R.id.action_loginFragment_to_rvCalendarFragment)
                    }
                }
                is LoginState.Invalid -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(context,getString(R.string.invalid_credentials),Toast.LENGTH_SHORT).show()
                }
                is LoginState.Error -> {
                        progressBar.visibility = View.GONE
                    Toast.makeText(context,getString(R.string.undefinded_error),Toast.LENGTH_SHORT).show()
                    }
                else -> LoginState.Idle
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}