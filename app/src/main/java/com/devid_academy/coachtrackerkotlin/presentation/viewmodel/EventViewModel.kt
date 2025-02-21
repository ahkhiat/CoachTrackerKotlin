package com.devid_academy.coachtrackerkotlin.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.coachtrackerkotlin.data.dto.EventTypeDTO
import com.devid_academy.coachtrackerkotlin.data.repository.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventViewModel: ViewModel() {

    private val repository = EventRepository()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _categories = MutableStateFlow<List<EventTypeDTO>>(emptyList())
    val categories: StateFlow<List<EventTypeDTO>> = _categories

    fun getEventTypes() {
        viewModelScope.launch {
            _isLoading.value = true
            val categories = withContext(Dispatchers.IO) {
                repository.getEventTypes()
            }
            _categories.value = categories
            Log.i("LIST VIEWMODEL", "LISTE DES CATEGORIES VIEW MODEL EVENT : ${_categories .value}")
            _isLoading.value = false
        }
    }
}