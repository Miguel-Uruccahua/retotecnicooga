package com.example.retotecnicooga.domain.details

import com.example.retotecnicooga.domain.model.Log
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    fun getAllLog(): Flow<List<Log>>
}