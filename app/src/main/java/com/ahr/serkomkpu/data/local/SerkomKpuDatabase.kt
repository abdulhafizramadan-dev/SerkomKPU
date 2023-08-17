package com.ahr.serkomkpu.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ElectorateEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SerkomKpuDatabase : RoomDatabase() {

    abstract val residentDao: ElectorateDao

    companion object {
        const val DATABASE_NAME = "kpu.db"
    }

}