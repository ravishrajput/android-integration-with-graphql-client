package com.ravish.android.integration.with.graphql.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    private val _allFlightLiveData = MutableLiveData<List<Flight>>()
    val allFlightLiveData: LiveData<List<Flight>>
        get() = _allFlightLiveData

    fun getAllFlights() {
        viewModelScope.launch {
            val response = apiRepository.getAllFlights()
            _allFlightLiveData.postValue(response)
        }
    }
}