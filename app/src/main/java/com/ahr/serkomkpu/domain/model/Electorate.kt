package com.ahr.serkomkpu.domain.model

import android.os.Parcelable
import com.ahr.serkomkpu.util.emptyString
import kotlinx.parcelize.Parcelize

@Parcelize
data class Electorate(
    val id: Int = 0,
    val image: String = emptyString(),
    val nik: String = emptyString(),
    val name: String = emptyString(),
    val phone: String = emptyString(),
    val gender: String = emptyString(),
    val dateCollectionDate: String = emptyString(),
    val address: String = emptyString()
) : Parcelable
