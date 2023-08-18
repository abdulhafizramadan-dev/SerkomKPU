package com.ahr.serkomkpu.presentation.list_electorate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.serkomkpu.domain.ElectorateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListElectorateViewModel @Inject constructor(
    private val electorateRepository: ElectorateRepository
) : ViewModel() {

    private val _listElectorateScreenUiState = MutableStateFlow(ListElectorateScreenUiState())
    val listElectorateScreenUiState get() = _listElectorateScreenUiState.asStateFlow()

    fun getAllElectorate() {
        viewModelScope.launch {
            electorateRepository.getAllElectorate().collect {
                _listElectorateScreenUiState.value = _listElectorateScreenUiState.value.copy(
                    electorates = it
                )
            }
        }
    }

    fun searchElectorate(query: String) {
        viewModelScope.launch {
            electorateRepository.searchElectorate(query).collect {
                _listElectorateScreenUiState.value = _listElectorateScreenUiState.value.copy(
                    electorates = it
                )
            }
        }
    }

    fun deleteElectorate(id: Int) {
        viewModelScope.launch {
            electorateRepository.deleteElectorate(id)
        }
    }


    fun updateSearchQuery(searchQuery: String) {
        _listElectorateScreenUiState.value = _listElectorateScreenUiState.value.copy(
            searchQuery = searchQuery
        )
    }

}