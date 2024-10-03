package com.example.retotecnicooga.data.repository

import com.example.retotecnicooga.data.local.database.application.ApplicationDao
import com.example.retotecnicooga.domain.model.Suggestion
import com.example.retotecnicooga.domain.suggestions.SuggestionsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SuggestionsRepositoryImpl @Inject constructor(
    private val applicationDao: ApplicationDao,
): SuggestionsRepository {

    override fun getLowerSuggestions(): Flow<List<Suggestion>> {
        return applicationDao.getLowerVersion()
    }

    override fun getPrioritySuggestions(): Flow<List<Suggestion>> {
        return applicationDao.getHighPriority()
    }

    override fun getAssignmentSuggestions(): Flow<List<Suggestion>> {
        return applicationDao.getOffAsigment()
    }

    override fun getDateEmptySuggestions(): Flow<List<Suggestion>> {
        return  applicationDao.getDateEmpty()
    }

    fun getStateDevelopment(): Flow<List<Suggestion>> {
        return  applicationDao.getStateDevelopment()
    }




}