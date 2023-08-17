package com.ahr.serkomkpu.domain

import com.ahr.serkomkpu.domain.model.Electorate
import kotlinx.coroutines.flow.Flow

interface ElectorateRepository {

    fun getAllElectorate(): Flow<List<Electorate>>

    fun searchElectorate(query: String): Flow<List<Electorate>>

    suspend fun getElectorate(id: Int): Electorate?

    fun getElectorateCount(): Flow<Int>

    suspend fun deleteAllElectorate()

    suspend fun deleteElectorate(id: Int)

    suspend fun upsertElectorate(electorate: Electorate)

}