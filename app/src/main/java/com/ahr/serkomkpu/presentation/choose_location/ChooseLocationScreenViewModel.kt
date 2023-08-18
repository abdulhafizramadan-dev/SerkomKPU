package com.ahr.serkomkpu.presentation.choose_location

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ChooseLocationScreenViewModel @Inject constructor() : ViewModel() {

    private val _chooseLocationScreenUiState = MutableStateFlow(ChooseLocationScreenUiState())
    val chooseLocationScreenUiState get() = _chooseLocationScreenUiState.asStateFlow()

    val btnSaveEnabled get() = _chooseLocationScreenUiState.map {
        it.locationCityName?.isNotEmpty() == true
    }

    fun updateCurrentLocation(latLng: LatLng) {
        _chooseLocationScreenUiState.value = _chooseLocationScreenUiState.value.copy(
            currentLocation = latLng
        )
    }

    fun updateLocationCityName(cityName: String) {
        _chooseLocationScreenUiState.value = _chooseLocationScreenUiState.value.copy(
            locationCityName = cityName
        )
    }

}