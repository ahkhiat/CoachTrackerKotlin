package com.devid_academy.coachtrackerkotlin.presentation.ui.shared.rvcalendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.data.dto.EventDTO
import com.devid_academy.coachtrackerkotlin.data.network.ApiService
import com.devid_academy.coachtrackerkotlin.data.dto.EventTypeDTO
import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RvCalendarViewModel @Inject constructor(
    private val api: ApiService,
): ViewModel() {


    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _categories = MutableLiveData<List<EventTypeDTO>>(emptyList())
    val categories: LiveData<List<EventTypeDTO>> = _categories

    private val _events = MutableLiveData<List<EventDTO>>(emptyList())
    val events: LiveData<List<EventDTO>> = _events

    fun getEvent() {
        viewModelScope.launch {
//            _sessionState.value = SessionState.Checking
            _isLoading.value = true
            val result = withContext(Dispatchers.IO) {
//                api.getApi().getAllEvents()
            }
//            if(result?.status == "unauthorized")
//                _sessionState.postValue(SessionState.Unauthorized)
//            else {
//                _sessionState.postValue(SessionState.Authorized)
//                _articles.postValue(result?.articles)
//            }
            _isLoading.postValue(false)
        }
    }

    fun getEventTypes() {
        viewModelScope.launch {
            _isLoading.value = true
            val categories = withContext(Dispatchers.IO) {
                api.getApi().getEventTypes()
            }
            _categories.value = categories
            _isLoading.value = false
        }
    }
}