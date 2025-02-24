package com.devid_academy.coachtrackerkotlin.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.dto.UserDTO
import com.devid_academy.coachtrackerkotlin.data.dto.auth.LoginDTO
import com.devid_academy.coachtrackerkotlin.data.manager.AuthManager
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.data.network.ApiService.getApi
import com.devid_academy.coachtrackerkotlin.data.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel() : ViewModel() {

    private val loginRepository = LoginRepository()

    private val _loginState = MutableLiveData<LoginState>(LoginState.Idle)
    val loginState: LiveData<LoginState> = _loginState

    fun verifyLogin(email: String, password: String) {
        _loginState.value = LoginState.Loading

        if (email.isNotEmpty() && password.isNotEmpty()) {

            viewModelScope.launch {
                val result = withContext(Dispatchers.IO) {
                    loginRepository.login(LoginDTO(email, password))
                }

                if (result.isSuccess) {
                    val statusAuth = result.getOrNull()
                    Log.d("LOGIN_VM", "Réponse de l'API : $statusAuth")

                    val isAuthValid = statusAuth?.token?.let {
                        Log.d("LOGIN_VM", "Token reçu : $it")

                        AuthManager.isTokenValid(it)
                    } == true
                    Log.d("LOGIN_VM", "isAuthValid = $isAuthValid")

                    if(isAuthValid) {
                        statusAuth?.token?.let {
                            PreferencesManager.setToken(it)
                        }
                        PreferencesManager.saveUser(getApi().getUserProfile())

                        Log.i("PROFILE", "PROFILE : ${PreferencesManager.getUser()}")

                        _loginState.value = LoginState.Success
                    } else {
                        Log.e("LOGIN_VM", "Token invalide, état mis à Invalid")

                        _loginState.value = LoginState.Invalid
                    }
                } else {
                    val errorMessage = result.exceptionOrNull()?.message ?: "Erreur inconnue"
                    _loginState.value = LoginState.Error(errorMessage)
                }

            }
        } else {
            _loginState.value = LoginState.Incomplete
        }
    }
    fun resetLoginState() {
        _loginState.value = LoginState.Idle
    }

}

sealed class LoginState {
    data object Idle : LoginState()
    data object Incomplete : LoginState()
    data object Loading : LoginState()
    data object Success : LoginState()
    data object Invalid : LoginState()
    data class Error(val message: String): LoginState()
}

