package com.example.retotecnicooga.data.repository

import com.example.retotecnicooga.data.local.database.appdetail.AppDetailDao
import com.example.retotecnicooga.data.local.database.appdetail.asDomain
import com.example.retotecnicooga.data.local.database.appdetail.asEntity
import com.example.retotecnicooga.data.local.database.application.ApplicationDao
import com.example.retotecnicooga.data.local.database.application.asDomain
import com.example.retotecnicooga.data.local.database.application.asEntity
import com.example.retotecnicooga.domain.actions.ActionsRepository
import com.example.retotecnicooga.domain.model.AppDetail
import com.example.retotecnicooga.domain.model.Application
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ActionsRepositoryImpl @Inject constructor(
    private val applicationDao: ApplicationDao,
    private val appDetailDao: AppDetailDao
): ActionsRepository {

    override suspend fun addApplication(application: Application) {
        applicationDao.insert(application.asEntity())
    }

    override suspend fun addAppDetail(appDetail: AppDetail) {
        val detail = appDetail.copy(dateCreated = getDate() )
        appDetailDao.insert(detail.asEntity())
    }

    override suspend fun deleteApplication(application: Application) {
        applicationDao.delete(application.asEntity())
    }

    override fun getApplications(): Flow<List<Application>>{
        return applicationDao.getAll().map { items->items.map{it.asDomain()}}
    }

    override suspend fun getAppDetail(application: Application): List<AppDetail> {
        return appDetailDao.getOneById(application.id).map { items-> items.asDomain() }
    }

    override suspend fun updateAppDetail(appDetail: AppDetail) {
        appDetailDao.update(appDetail.asEntity())
    }

    private fun getDate(pattern: String = "yyyyMMdd"):String{
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }



}