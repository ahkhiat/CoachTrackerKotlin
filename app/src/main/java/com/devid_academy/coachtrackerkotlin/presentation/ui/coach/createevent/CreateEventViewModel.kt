package com.devid_academy.coachtrackerkotlin.presentation.ui.coach.createevent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.data.dto.SeasonDTO
import com.devid_academy.coachtrackerkotlin.data.dto.StadiumDTO
import com.devid_academy.coachtrackerkotlin.data.dto.VisitorTeamDTO
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import com.devid_academy.coachtrackerkotlin.data.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel  @Inject constructor(
    private val api: ApiService,
    private val pm: PreferencesManager
): ViewModel() {


    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _visitorTeamList = MutableLiveData<List<VisitorTeamDTO>>(emptyList())
    val visitorTeamList: LiveData<List<VisitorTeamDTO>> = _visitorTeamList

    private val _stadiumList = MutableLiveData<List<StadiumDTO>>(emptyList())
    val stadiumList: LiveData<List<StadiumDTO>> = _stadiumList

    private val _seasonList = MutableLiveData<List<SeasonDTO>>(emptyList())
    val seasonList: LiveData<List<SeasonDTO>> = _seasonList

    init {
        getVisitorTeamList()
        getStadiumList()
        getSeasonList()
    }

    fun createEvent(
        eventType: Int,
        date: String,
        stadium: Int,
        season: Int,
        visitorTeam: Int
    ) {
        //team ID
        if(eventType.isNot)
    }

    fun getVisitorTeamList() {
        viewModelScope.launch {
            _isLoading.value = true
            val visitorTeamList = withContext(Dispatchers.IO) {
                api.getApi().getVisitorTeamList()
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
                api.getApi().getStadiumList()
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
                api.getApi().getSeasonList()
            }
            _seasonList.value = seasonList
            Log.i("VIEW MODEL SPINNER", "LISTE DES SEASON: ${_seasonList .value}")
            _isLoading.value = false
        }
    }

}