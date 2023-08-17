package com.ahr.serkomkpu.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _loginScreenUiState = MutableStateFlow(LoginScreenUiState())
    val loginScreenUiState get() = _loginScreenUiState.asStateFlow()

    val btnLoginEnabled get() = _loginScreenUiState.map {
        it.email.isNotEmpty() && it.password.length >= 6
    }

    fun updateEmail(email: String) {
        _loginScreenUiState.value = _loginScreenUiState.value.copy(
            email = email
        )
    }

    fun updatePassword(password: String) {
        _loginScreenUiState.value = _loginScreenUiState.value.copy(
            password = password
        )
    }

}