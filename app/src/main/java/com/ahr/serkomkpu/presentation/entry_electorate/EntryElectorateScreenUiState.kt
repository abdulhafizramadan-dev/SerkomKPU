package com.ahr.serkomkpu.presentation.entry_electorate

import androidx.compose.ui.graphics.ImageBitmap
import com.ahr.serkomkpu.util.emptyString
import java.time.LocalDate

data class EntryElectorateScreenUiState(
    val image: String = emptyString(),
    val nik: String = emptyString(),
    val name: String = emptyString(),
    val phone: String = emptyString(),
    val gender: String = emptyString(),
    val dateCollectionDate: LocalDate = LocalDate.now(),
    val address: String = emptyString(),
    val imageBitmap: ImageBitmap? = null
)
