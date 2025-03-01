package com.devid_academy.coachtrackerkotlin.presentation.ui.shared.rvteam

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.data.dto.TeamDTO
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.data.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val api: ApiService,
    private val preferencesManager: PreferencesManager
    ): ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _teamLiveData = MutableLiveData<TeamDTO?>()
    val teamLiveData : LiveData<TeamDTO?> = _teamLiveData

    init {
        getTeam()
    }
    fun getTeam() {
        viewModelScope.launch {
            _isLoading.value = true
            _teamLiveData.value = null
            val teamId = preferencesManager.getTeamId()
            Log.i("TEAM VM", "Team id recuperé dans le PM : $teamId")
            try {
                val result = withContext(Dispatchers.IO) {
                    api.getApi().getTeam(teamId)
                }
                Log.i("VM TEAM", "VM TEAM : $result")
                _teamLiveData.value = result
            } catch (e : Exception) {
                Log.e("VM TEAM", "Erreur API : ${e.message}", e)
            }
            _isLoading.value = false
        }
    }
    fun onLogout() {
        _teamLiveData.value = null
    }


}