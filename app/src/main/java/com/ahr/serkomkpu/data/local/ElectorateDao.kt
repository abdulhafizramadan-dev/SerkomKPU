package com.ahr.serkomkpu.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ElectorateDao {

    @Query("SELECT * FROM electorateentity ORDER BY id DESC")
    fun getAllElectorate(): Flow<List<ElectorateEntity>>

    @Query("SELECT * FROM electorateentity WHERE name LIKE '%' || :query || '%' OR address LIKE '%' || :query || '%' OR nik LIKE '%' || :query || '%' ORDER BY id DESC")
    fun searchElectorate(query: String): Flow<List<ElectorateEntity>>

    @Query("SELECT * FROM electorateentity WHERE id = :id")
    suspend fun getElectorate(id: Int): ElectorateEntity?

    @Query("SELECT COUNT(id) FROM electorateentity")
    fun getElectorateCount(): Flow<Int>

    @Query("DELETE FROM electorateentity")
    suspend fun deleteAllElectorate()

    @Query("DELETE FROM electorateentity WHERE id = :id")
    suspend fun deleteElectorate(id: Int)

    @Upsert
    suspend fun upsertElectorate(electorateEntity: ElectorateEntity)

}