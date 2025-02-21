package com.devid_academy.coachtrackerkotlin.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.data.dto.auth.LoginDTO
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.data.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel: ViewModel() {

    private val loginRepository = LoginRepository()
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun verifyLogin(user: LoginDTO) {
        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                loginRepository.login(user)
            }
            val statusAuth = result.getOrNull()

            if (result.isSuccess && statusAuth !=null) {
                Log.i("LOGIN VM", "LOGIN VM, StatusAuth : $statusAuth")
                PreferencesManager.setToken(statusAuth.token!!)
                _loginState.value = LoginState.Success
            } else {
                _loginState.value = LoginState.Error(result.exceptionOrNull()?.message!!)
            }
        }
    }
}

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

