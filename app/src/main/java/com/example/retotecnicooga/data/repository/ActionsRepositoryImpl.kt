package com.example.retotecnicooga.data.repository

import com.example.retotecnicooga.data.local.database.application.ApplicationDao
import com.example.retotecnicooga.domain.actions.ActionsRepository
import javax.inject.Inject

class ActionsRepositoryImpl @Inject constructor(
    private val applicationDao: ApplicationDao
): ActionsRepository {

    override suspend fun add() {
    }

}