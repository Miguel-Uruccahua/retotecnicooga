package com.example.retotecnicooga.domain.actions

import com.example.retotecnicooga.domain.model.AppDetail
import com.example.retotecnicooga.domain.model.Application
import kotlinx.coroutines.flow.Flow

interface ActionsRepository {
    suspend fun addApplication(application: Application)

    suspend fun addAppDetail(appDetail: AppDetail)

    suspend fun deleteApplication(application: Application)

     fun getApplications(): Flow<List<Application>>

     suspend fun getAppDetail(application: Application): List<AppDetail>

     suspend fun updateAppDetail(appDetail: AppDetail)
     suspend fun insertData()

}