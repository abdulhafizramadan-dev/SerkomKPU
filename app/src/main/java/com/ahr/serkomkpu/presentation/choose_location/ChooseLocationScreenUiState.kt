package com.ahr.serkomkpu.presentation.choose_location

import com.google.android.gms.maps.model.LatLng

data class ChooseLocationScreenUiState(
    val currentLocation: LatLng? = null,
    val locationCityName: String? = null
)
