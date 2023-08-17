package com.ahr.serkomkpu.presentation.list_electorate

import com.ahr.serkomkpu.domain.model.Electorate
import com.ahr.serkomkpu.util.emptyString

data class ListElectorateScreenUiState(
    val searchQuery: String = emptyString(),
    val electorates: List<Electorate> = emptyList(),
)
