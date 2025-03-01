package com.devid_academy.coachtrackerkotlin.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.data.dto.auth.LoginDTO
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.data.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val api: ApiService
): ViewModel() {

    private val _loginState = MutableLiveData<LoginState>(LoginState.Idle)
    val loginState: LiveData<LoginState> = _loginState

    fun verifyLogin(email: String, password: String) {
        _loginState.value = LoginState.Loading
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val response = withContext(Dispatchers.IO) {
                        api.getApi().loginUser(LoginDTO(email, password))
                    }
                    if (response.isSuccessful) {
                        val result = response.body()

                        if (result?.token != null) {
                            preferencesManager.setToken(result.token)
                            val userProfile = withContext(Dispatchers.IO) {
                               api.getApi().getUserProfile()
                            }
                            Log.i("USER PROFILE", "User profile : ${userProfile}")
                            preferencesManager.saveUser(userProfile)
                            Log.i("SAVED USER", "User saved in preferences: ${preferencesManager.getUser()}")
                            val teamId = userProfile.isCoachOf?.id
                                ?: userProfile.playsIn?.id
                                ?: userProfile.isParentOf?.firstOrNull()?.playsIn?.id
                            Log.i("TEAM ID", "TEAM ID : $teamId")
                            teamId?.let {
                                preferencesManager.setTeamId(it)
                            }
                        }
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

