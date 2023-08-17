package com.ahr.serkomkpu.presentation.detail_electorate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.serkomkpu.domain.ElectorateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailElectorateViewModel @Inject constructor(
    private val electorateRepository: ElectorateRepository
) : ViewModel() {

    private val _detailElectorateScreenUiState = MutableStateFlow(DetailElectorateScreenUiState())
    val detailElectorateScreenUiState get() = _detailElectorateScreenUiState.asStateFlow()

    fun getElectorate(id: Int) {
        viewModelScope.launch {
            val electorate = electorateRepository.getElectorate(id)
            _detailElectorateScreenUiState.value = _detailElectorateScreenUiState.value.copy(
                electorate = electorate
            )
        }
    }

}