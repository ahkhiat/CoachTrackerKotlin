package com.devid_academy.coachtrackerkotlin.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.data.dto.auth.LoginDTO
import com.devid_academy.coachtrackerkotlin.data.dto.auth.RegisterDTO
import com.devid_academy.coachtrackerkotlin.data.dto.auth.StatusAuthDTO
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.data.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager ,
    private val api: ApiService
) : ViewModel() {

    private val _registerState = MutableLiveData<RegisterState>(RegisterState.Idle)
    val registerState: LiveData<RegisterState> = _registerState

//    private var result : Result<StatusAuthDTO>? = null

    fun register(email: String, password: String,
                 passwordConfirm: String, firstname: String,
                 lastname: String, birthdate: String) {
        _registerState.value = RegisterState.Loading
        if(email.isNotEmpty() && password.isNotEmpty()
            && passwordConfirm.isNotEmpty() && firstname.isNotEmpty()
            && lastname.isNotEmpty() && birthdate.isNotEmpty()) {

            if(password == passwordConfirm) {
                viewModelScope.launch {
                    val response = withContext(Dispatchers.IO) {
                        api.getApi().registerUser(RegisterDTO(email, password, firstname,
                                                        lastname, birthdate))
                    }
                    if (response.isSuccessful) {
                        val result = response.body()
                        preferencesManager.setToken(result?.token!!)
                        _registerState.value = RegisterState.Success
                    } else when (response.code()) {
                        500 -> {
                        _registerState.value = RegisterState.Error
                        Log.d("RESULT CODE 500", "RESULT CODE 500")
                        }
                        400 -> {
                            _registerState.value = RegisterState.Error
                            Log.d("RESULT CODE 400", "RESULT CODE 400")
                        }
                    }
                }
            } else {
                _registerState.value = RegisterState.PasswordsDifferent
            }
        } else {
            _registerState.value = RegisterState.Incomplete
        }
    }
}

sealed class RegisterState {
    data object Idle : RegisterState()
    data object Incomplete : RegisterState()
    data object Loading : RegisterState()
    data object Success : RegisterState()
    data object UsernameAlreadyExists: RegisterState()
    data object PasswordsDifferent: RegisterState()
    data object NotCreated : RegisterState()
    data object Error : RegisterState()
}
