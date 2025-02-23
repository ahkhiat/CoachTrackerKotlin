package com.devid_academy.coachtrackerkotlin.presentation.ui.shared.rvteam

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.data.network.ApiService.getApi
import com.devid_academy.coachtrackerkotlin.data.dto.TeamDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamViewModel: ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _teamLiveData = MutableLiveData<TeamDTO>()
    val teamLiveData : LiveData<TeamDTO> = _teamLiveData

    init {
        Log.d("VM TEAM INIT", "ViewModel initialisé, lancement de getTeam()")
        getTeam()
    }
    fun getTeam() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = withContext(Dispatchers.IO) {
                    getApi().getTeam(3)
                }
                Log.i("VM TEAM", "VM TEAM : $result")
                _teamLiveData.value = result
            } catch (e : Exception) {
                Log.e("VM TEAM", "Erreur API : ${e.message}", e)
            }
            _isLoading.value = false
        }
    }


}