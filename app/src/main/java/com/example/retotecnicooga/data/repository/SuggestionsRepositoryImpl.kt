package com.example.retotecnicooga.data.repository

import com.example.retotecnicooga.data.local.database.application.ApplicationDao
import com.example.retotecnicooga.domain.model.Suggestion
import com.example.retotecnicooga.domain.suggestions.SuggestionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.forEach
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

    override fun getDuplicateType(): Flow<List<Suggestion>> {
        val list = applicationDao.getAll()
        return  flow {
            list.collect{ item->
                item.forEach { itemApp->
                    item.find {
                        it.type == itemApp.type && it.id != itemApp.id
                    }.let {
                        if (it!=null){
                            emit(listOf(Suggestion(
                                appName = "${it.name} - ${itemApp.name}",
                                detail = "Pueden Fusionarse",
                                type = it.type
                            )))
                        }
                    }
                }
            }
        }
    }



}