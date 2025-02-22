package com.devid_academy.coachtrackerkotlin.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.data.dto.auth.LoginDTO
import com.devid_academy.coachtrackerkotlin.data.dto.auth.RegisterDTO
import com.devid_academy.coachtrackerkotlin.data.dto.auth.StatusAuthDTO
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.data.repository.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel() : ViewModel() {

    private val registerRepository = RegisterRepository()
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState
    private var result : Result<StatusAuthDTO>? = null


    fun register(user: RegisterDTO) {
        _registerState.value = RegisterState.Loading

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                registerRepository.register(user)
            }
            val statusAuth = result.getOrNull()

            if (result.isSuccess && statusAuth !=null) {
                Log.i("REGISTER VM", "REGISTER VM, StatusAuth : $statusAuth")
                PreferencesManager.setToken(statusAuth.token!!)
                _registerState.value = RegisterState.Success
            } else {
                _registerState.value = RegisterState.Error(result.exceptionOrNull()?.message!!)
            }
        }
    }
}

sealed class RegisterState {
    data object Idle : RegisterState()
    data object Loading : RegisterState()
    data object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}
