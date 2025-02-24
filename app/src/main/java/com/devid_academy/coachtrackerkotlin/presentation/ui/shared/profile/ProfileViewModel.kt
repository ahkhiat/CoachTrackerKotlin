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
import com.devid_academy.coachtrackerkotlin.data.network.ApiService.getApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(): ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _userLiveData = MutableLiveData<UserProfileDTO>()
    val userLiveData: LiveData<UserProfileDTO> = _userLiveData

    init {
        getUserPofile()
    }

    fun getUserPofile() {

        _userLiveData.value = PreferencesManager.getUser()

//        viewModelScope.launch {
//            _isLoading.value = true
//            try {
//                val result = withContext(Dispatchers.IO) {
//                    getApi().getUserProfile()
//                }
//                Log.i("VM PROFILE", "VM PROFILE : $result")
//                _userLiveData.value = result
//            } catch (e : Exception) {
//                Log.e("VM PROFILE", "Erreur API : ${e.message}", e)
//            }
//            _isLoading.value = false
//        }
    }




}