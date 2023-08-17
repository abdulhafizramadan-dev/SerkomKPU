package com.ahr.serkomkpu.presentation.entry_electorate

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.serkomkpu.domain.ElectorateRepository
import com.ahr.serkomkpu.domain.model.Electorate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class EntryElectorateViewModel @Inject constructor(
    private val electorateRepository: ElectorateRepository
) : ViewModel() {

    private val _entryElectorateScreenUiState = MutableStateFlow(EntryElectorateScreenUiState())
    val entryElectorateScreenUiState get() = _entryElectorateScreenUiState.asStateFlow()

    val btnAddEnabled get() = _entryElectorateScreenUiState.map {
        it.image.isNotEmpty() && it.nik.isNotEmpty() &&
        it.name.isNotEmpty() && it.phone.isNotEmpty() &&
        it.gender.isNotEmpty() && it.address.isNotEmpty() &&
        it.imageBitmap != null
    }

    fun addEntryElectorate() {
        val electorate = with(_entryElectorateScreenUiState.value) {
            Electorate(
                image = image,
                nik = nik,
                name = name,
                phone = phone,
                gender = gender,
                dateCollectionDate = dateCollectionDate,
                address = address
            )
        }
        viewModelScope.launch {
            electorateRepository.upsertElectorate(
                electorate
            )
        }
    }

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

    fun updateImageBitmap(imageBitmap: ImageBitmap) {
        _entryElectorateScreenUiState.value = _entryElectorateScreenUiState.value.copy(
            imageBitmap = imageBitmap
        )
    }

}