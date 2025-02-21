package com.devid_academy.coachtrackerkotlin.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.data.dto.EventTypeDTO
import com.devid_academy.coachtrackerkotlin.data.dto.SeasonDTO
import com.devid_academy.coachtrackerkotlin.data.dto.StadiumDTO
import com.devid_academy.coachtrackerkotlin.data.dto.VisitorTeamDTO
import com.devid_academy.coachtrackerkotlin.data.repository.SpinnerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpinnerViewModel: ViewModel() {

    private val repository = SpinnerRepository()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _visitorTeamList = MutableStateFlow<List<VisitorTeamDTO>>(emptyList())
    val visitorTeamList: StateFlow<List<VisitorTeamDTO>> = _visitorTeamList

    private val _stadiumList = MutableStateFlow<List<StadiumDTO>>(emptyList())
    val stadiumList: StateFlow<List<StadiumDTO>> = _stadiumList

    private val _seasonList = MutableStateFlow<List<SeasonDTO>>(emptyList())
    val seasonList: StateFlow<List<SeasonDTO>> = _seasonList

    fun getVisitorTeamList() {
        viewModelScope.launch {
            _isLoading.value = true
            val visitorTeamList = withContext(Dispatchers.IO) {
                repository.getVisitorTeamList()
            }
            _visitorTeamList.value = visitorTeamList
            Log.i("VIEW MODEL SPINNER", "LISTE DES VISITOR TEAM: ${_visitorTeamList .value}")
            _isLoading.value = false
        }
    }

    fun getStadiumList() {
        viewModelScope.launch {
            _isLoading.value = true
            val stadiumList = withContext(Dispatchers.IO) {
                repository.getStadiumList()
            }
            _stadiumList.value = stadiumList
            Log.i("VIEW MODEL SPINNER", "LISTE DES STADIUM: ${_stadiumList .value}")
            _isLoading.value = false
        }
    }

    fun getSeasonList(){
        viewModelScope.launch {
            _isLoading.value = true
            val seasonList = withContext(Dispatchers.IO) {
                repository.getSeasonList()
            }
            _seasonList.value = seasonList
            Log.i("VIEW MODEL SPINNER", "LISTE DES SEASON: ${_seasonList .value}")
            _isLoading.value = false
        }
    }

}