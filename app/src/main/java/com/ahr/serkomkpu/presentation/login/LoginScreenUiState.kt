package com.ahr.serkomkpu.presentation.login

import com.ahr.serkomkpu.util.emptyString

data class LoginScreenUiState(
    val email: String = emptyString(),
    val password: String = emptyString(),
)
