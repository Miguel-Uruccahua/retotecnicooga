package com.example.retotecnicooga.data.repository

import com.example.retotecnicooga.data.local.database.log.LogDao
import com.example.retotecnicooga.data.local.database.log.asDomain
import com.example.retotecnicooga.domain.details.DetailsRepository
import com.example.retotecnicooga.domain.model.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val logDao: LogDao
): DetailsRepository {

    override fun getAllLog(): Flow<List<Log>> {
        return logDao.getAll().map { items -> items.map { it.asDomain()} }
    }

}
