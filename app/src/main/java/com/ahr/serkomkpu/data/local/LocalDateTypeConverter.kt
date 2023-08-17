package com.ahr.serkomkpu.data.local

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateTypeConverter {

    @TypeConverter
    fun from(localDate: LocalDate): Long {
        return localDate.toEpochDay()
    }

    @TypeConverter
    fun to(epochDay: Long): LocalDate {
        return LocalDate.ofEpochDay(epochDay)
    }

}