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
import com.devid_academy.coachtrackerkotlin.data.network.ApiService
import com.devid_academy.coachtrackerkotlin.data.network.ApiService.getApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel() : ViewModel() {

    private val preferencesManager = PreferencesManager
    private val api = ApiService.getApi()
//    private val loginRepository = LoginRepository()

    private val _loginState = MutableLiveData<LoginState>(LoginState.Idle)
    val loginState: LiveData<LoginState> = _loginState

    fun verifyLogin(email: String, password: String) {
        _loginState.value = LoginState.Loading
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val response = withContext(Dispatchers.IO) {
                        api.loginUser(LoginDTO(email, password))
                    }
                    if (response.isSuccessful) {
                        val result = response.body()
                        preferencesManager.setToken(result!!.token!!)
                        _loginState.value = LoginState.Success
                    } else if(response.code() == 401) {
                        _loginState.value = LoginState.Invalid
                        Log.d("RESULT CODE 401", "RESULT CODE 401")
                    }
                } catch (e: Exception) {
                    Log.e("Error LoginVM", "Erreur Login VM : ${e.message}")
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
    data object Error: LoginState()
}

