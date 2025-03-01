package com.devid_academy.coachtrackerkotlin.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.manager.AuthManager
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val authManager: AuthManager
): ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _direction = MutableLiveData<Int?>()
    val direction: LiveData<Int?> = _direction

    init {
        verifyToken()
    }

    fun verifyToken() {
        val token = preferencesManager.getToken()
        viewModelScope.launch {
            _isLoading.value = true
            delay(3000)
            _direction.value =
                if(token.isNullOrEmpty() || !authManager.isTokenValid(token))
                    R.id.action_splashFragment_to_loginFragment
                else
                    R.id.action_splashFragment_to_rvCalendarFragment

            _isLoading.value = false
        }
    }
}