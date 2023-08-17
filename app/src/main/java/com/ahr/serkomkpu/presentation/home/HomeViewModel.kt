package com.ahr.serkomkpu.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.serkomkpu.domain.ElectorateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val electorateRepository: ElectorateRepository
) : ViewModel() {

    private val _homeScreenUiState = MutableStateFlow(HomeScreenUiState())
    val homeScreenUiState get() = _homeScreenUiState.asStateFlow()

    fun getElectorateCount() {
        viewModelScope.launch {
            electorateRepository.getElectorateCount().collectLatest {
                _homeScreenUiState.value = _homeScreenUiState.value.copy(
                    electorateCount = it
                )
            }
        }
    }

}