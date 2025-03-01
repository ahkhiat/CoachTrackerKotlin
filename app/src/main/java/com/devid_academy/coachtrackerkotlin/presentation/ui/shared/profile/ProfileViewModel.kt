package com.devid_academy.coachtrackerkotlin.presentation.ui.shared.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.data.dto.TeamDTO
import com.devid_academy.coachtrackerkotlin.data.dto.UserDTO
import com.devid_academy.coachtrackerkotlin.data.dto.UserProfileDTO
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.data.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val api: ApiService,
    private val preferencesManager: PreferencesManager
): ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _userLiveData = MutableLiveData<UserProfileDTO>()
    val userLiveData: LiveData<UserProfileDTO> = _userLiveData

    private val _teamNameLiveData = MutableLiveData<String>()
    val teamNameLiveData: LiveData<String> = _teamNameLiveData

    init {
        getUserPofile()
    }

    fun getUserPofile() {

        _userLiveData.value = preferencesManager.getUser()

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = withContext(Dispatchers.IO) {
                    api.getApi().getUserProfile()
                }
                _userLiveData.value = result
                _teamNameLiveData.value = result.isCoachOf?.name ?: result.playsIn?.name
                Log.i("VM PROFILE", "VM COACH OU PLAYSIN : ${result.isCoachOf?.name}")

            } catch (e : Exception) {
                Log.e("VM PROFILE", "Erreur API : ${e.message}", e)
            }
            _isLoading.value = false
        }
    }




}