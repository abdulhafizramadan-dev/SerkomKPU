package com.ahr.serkomkpu.domain.model

import android.os.Parcelable
import com.ahr.serkomkpu.util.emptyString
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Electorate(
    val id: Int = 0,
    val image: String = emptyString(),
    val nik: String = emptyString(),
    val name: String = emptyString(),
    val phone: String = emptyString(),
    val gender: String = emptyString(),
    val dateCollectionDate: LocalDate = LocalDate.now(),
    val address: String = emptyString()
) : Parcelable
