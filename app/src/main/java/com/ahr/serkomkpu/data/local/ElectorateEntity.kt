package com.ahr.serkomkpu.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ahr.serkomkpu.util.emptyString
import java.time.LocalDate

@Entity
@TypeConverters(LocalDateTypeConverter::class)
data class ElectorateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: String = emptyString(),
    val nik: String = emptyString(),
    val name: String = emptyString(),
    val phone: String = emptyString(),
    val gender: String = emptyString(),
    val dateCollectionDate: LocalDate = LocalDate.now(),
    val address: String = emptyString()
)
