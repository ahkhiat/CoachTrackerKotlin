package com.devid_academy.coachtrackerkotlin.presentation.ui.shared.rvcalendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.data.network.ApiService
import com.devid_academy.coachtrackerkotlin.data.dto.EventTypeDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RvCalendarViewModel: ViewModel() {

    private val api = ApiService.getApi()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _categories = MutableLiveData<List<EventTypeDTO>>(emptyList())
    val categories: LiveData<List<EventTypeDTO>> = _categories

    fun getEventTypes() {
        viewModelScope.launch {
            _isLoading.value = true
            val categories = withContext(Dispatchers.IO) {
                api.getEventTypes()
            }
            _categories.value = categories
            _isLoading.value = false
        }
    }
}