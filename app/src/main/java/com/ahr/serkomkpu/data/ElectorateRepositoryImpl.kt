package com.ahr.serkomkpu.data

import com.ahr.serkomkpu.data.local.SerkomKpuDatabase
import com.ahr.serkomkpu.data.mapper.toDomain
import com.ahr.serkomkpu.data.mapper.toDomains
import com.ahr.serkomkpu.data.mapper.toEntity
import com.ahr.serkomkpu.domain.ElectorateRepository
import com.ahr.serkomkpu.domain.model.Electorate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ElectorateRepositoryImpl @Inject constructor(
    private val serkomKpuDatabase: SerkomKpuDatabase
) : ElectorateRepository {

    private val electorateDao get() = serkomKpuDatabase.residentDao

    override fun getAllElectorate(): Flow<List<Electorate>> {
        return electorateDao.getAllElectorate().map { it.toDomains() }
    }

    override fun searchElectorate(query: String): Flow<List<Electorate>> {
        return electorateDao.searchElectorate(query).map { it.toDomains() }
    }

    override suspend fun getElectorate(id: Int): Electorate? {
        return electorateDao.getElectorate(id)?.toDomain()
    }

    override fun getElectorateCount(): Flow<Int> {
        return electorateDao.getElectorateCount()
    }

    override suspend fun deleteAllElectorate() {
        electorateDao.deleteAllElectorate()
    }

    override suspend fun deleteElectorate(id: Int) {
        electorateDao.deleteElectorate(id)
    }

    override suspend fun upsertElectorate(electorate: Electorate) {
        electorateDao.upsertElectorate(electorate.toEntity())
    }

}