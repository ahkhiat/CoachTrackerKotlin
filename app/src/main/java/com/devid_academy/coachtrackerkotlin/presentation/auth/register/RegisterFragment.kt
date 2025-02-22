package com.devid_academy.coachtrackerkotlin.presentation.auth.register

import android.app.DatePickerDialog
import android.icu.util.Calendar
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
import com.devid_academy.coachtrackerkotlin.data.dto.auth.RegisterDTO
import com.devid_academy.coachtrackerkotlin.databinding.FragmentRegisterBinding
import com.devid_academy.coachtrackerkotlin.presentation.auth.login.LoginFragment
import com.devid_academy.coachtrackerkotlin.presentation.ui.shared.rvcalendar.RvCalendarFragment
import com.devid_academy.coachtrackerkotlin.presentation.viewmodel.RegisterState
import com.devid_academy.coachtrackerkotlin.presentation.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by activityViewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var message: String

    private lateinit var loginForm : String
    private lateinit var passwordForm: String
    private lateinit var passwordConfirmForm : String
    private lateinit var firstnameForm : String
    private lateinit var lastnameForm : String
    private lateinit var birthdateForm : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = binding.progressBar

        observeRegisterState()

        with(binding) {

            registerBtnRegister.setOnClickListener {

                loginForm = registerEtEmail.text.toString().trim()
                passwordForm = registerEtPassword.text.toString().trim()
                passwordConfirmForm = registerEtPasswordConfirm.text.toString().trim()
                firstnameForm = registerEtFirstname.text.toString().trim()
                lastnameForm = registerEtLastname.text.toString().trim()
                birthdateForm = registerEtBirthdate.text.toString().trim()

                if (passwordForm != passwordConfirmForm) {
                    Toast.makeText(context, getString(R.string.passwords_differents), Toast.LENGTH_SHORT).show()
                } else if (
                    loginForm.isNotEmpty() && passwordForm.isNotEmpty() &&
                    firstnameForm.isNotEmpty() && lastnameForm.isNotEmpty() &&
                    birthdateForm.isNotEmpty()
                ) {
                    viewModel.register(RegisterDTO(loginForm, passwordForm,
                                                firstnameForm, lastnameForm,
                                                birthdateForm))
                } else {
                    Toast.makeText(context, getString(R.string.fill_all_inputs), Toast.LENGTH_SHORT).show()
                }
            }

            registerEtBirthdate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                    registerEtBirthdate.setText(formattedDate)
                }, year, month, day)

                datePickerDialog.show()
            }

            registerTvSignup.setOnClickListener {
                parentFragmentManager.commit {
                    replace(R.id.fg_container, LoginFragment())
                    addToBackStack(null)
                }
            }
        }
    }

    private fun observeRegisterState() {
        lifecycleScope.launch {
            viewModel.registerState.collect {
                when (it) {
                    is RegisterState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is RegisterState.Success -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, "Connexion réussie", Toast.LENGTH_SHORT).show()

                        parentFragmentManager.commit {
                            replace(R.id.fg_container, RvCalendarFragment())
                        }
                    }
                    is RegisterState.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> RegisterState.Idle


                }
            }
        }
    }
}