package com.ahr.serkomkpu.presentation.entry_electorate

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class EntryElectorateViewModel : ViewModel() {

    private val _entryElectorateScreenUiState = MutableStateFlow(EntryElectorateScreenUiState())
    val entryElectorateScreenUiState get() = _entryElectorateScreenUiState.asStateFlow()

    fun updateImage(image: String) {
        _entryElectorateScreenUiState.value = _entryElectorateScreenUiState.value.copy(
            image = image
        )
    }

    fun updateNik(nik: String) {
        _entryElectorateScreenUiState.value = _entryElectorateScreenUiState.value.copy(
            nik = nik
        )
    }

    fun updateName(name: String) {
        _entryElectorateScreenUiState.value = _entryElectorateScreenUiState.value.copy(
            name = name
        )
    }

    fun updatePhone(phone: String) {
        _entryElectorateScreenUiState.value = _entryElectorateScreenUiState.value.copy(
            phone = phone
        )
    }

    fun updateGender(gender: String) {
        _entryElectorateScreenUiState.value = _entryElectorateScreenUiState.value.copy(
            gender = gender
        )
    }

    fun updateDateCollectionDate(dateCollectionDate: LocalDate) {
        _entryElectorateScreenUiState.value = _entryElectorateScreenUiState.value.copy(
            dateCollectionDate = dateCollectionDate
        )
    }

    fun updateAddress(address: String) {
        _entryElectorateScreenUiState.value = _entryElectorateScreenUiState.value.copy(
            address = address
        )
    }

}