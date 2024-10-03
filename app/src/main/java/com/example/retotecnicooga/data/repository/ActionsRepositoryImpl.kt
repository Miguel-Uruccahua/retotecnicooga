package com.example.retotecnicooga.data.repository

import com.example.retotecnicooga.data.local.database.appdetail.AppDetailDao
import com.example.retotecnicooga.data.local.database.appdetail.asDomain
import com.example.retotecnicooga.data.local.database.appdetail.asEntity
import com.example.retotecnicooga.data.local.database.application.ApplicationDao
import com.example.retotecnicooga.data.local.database.application.asDomain
import com.example.retotecnicooga.data.local.database.application.asEntity
import com.example.retotecnicooga.data.local.database.log.LogDao
import com.example.retotecnicooga.data.local.database.log.LogEntity
import com.example.retotecnicooga.domain.actions.ActionsRepository
import com.example.retotecnicooga.domain.model.AppDetail
import com.example.retotecnicooga.domain.model.Application
import com.example.retotecnicooga.domain.model.LogDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ActionsRepositoryImpl @Inject constructor(
    private val applicationDao: ApplicationDao,
    private val appDetailDao: AppDetailDao,
    private val logDao: LogDao
) : ActionsRepository {

    override suspend fun addApplication(application: Application) {
        val id = applicationDao.insert(application.asEntity())
        generateLog(application.name, id.toInt(), 0, LogDetail.ADDAPP)
    }

    override suspend fun addAppDetail(appDetail: AppDetail) {
        val detail = appDetail.copy(dateCreated = getDate())
        val id = appDetailDao.insert(detail.asEntity())
        generateLog(appDetail.title, 0, id.toInt(), LogDetail.ADDAPPDETAIL)
    }

    override suspend fun deleteApplication(application: Application) {
        applicationDao.delete(application.asEntity())
        generateLog(application.name, application.id, 0, LogDetail.DETELEAPP)
    }

    override fun getApplications(): Flow<List<Application>> {
        return applicationDao.getAll().map { items -> items.map { it.asDomain() } }
    }

    override suspend fun getAppDetail(application: Application): List<AppDetail> {
        return appDetailDao.getOneById(application.id).map { items -> items.asDomain() }
    }

    override suspend fun updateAppDetail(appDetail: AppDetail) {
        appDetailDao.update(appDetail.asEntity())
        generateLog(
            appDetail.title,
            appDetail.idApplication,
            appDetail.id,
            LogDetail.UPDATEAPPDETAIL
        )
    }

    private fun getDate(pattern: String = "yyyyMMdd"): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }

    private suspend fun generateLog(
        reference: String,
        idApplication: Int,
        idAppDetail: Int,
        logDetail: LogDetail
    ) {
        logDao.insert(
            LogEntity(
                id = 0,
                action = logDetail,
                idAppDetail = idAppDetail,
                idApplication = idApplication,
                reference = reference,
                date = getDate()
            )
        )
    }

    override suspend fun insertData() {
        val data = applicationDao.getOne()
        val log = logDao.getAllOne()
        if ( data==null && log.isEmpty()) {
            val applicationList = listOf(
                Application(
                    id = 1,
                    name = "WhatsApp",
                    type = "Social",
                    minCompatibility = "Android 8",
                    maxCompatibility = "Android 14",
                    state = "Desarrollo",
                    isUpToolsFollow = true
                ),
                Application(
                    id = 2,
                    name = "Instagram",
                    type = "Social",
                    minCompatibility = "Android 8",
                    maxCompatibility = "Android 14",
                    state = "Desarrollo",
                    isUpToolsFollow = false
                ),
                Application(
                    id = 3,
                    name = "Facebook",
                    type = "Social Media",
                    minCompatibility = "Android 8",
                    maxCompatibility = "Android 14",
                    state = "Desarrollo",
                    isUpToolsFollow = true
                )
            )
            applicationDao.insertAll(applicationList.map { it.asEntity() })
        }
    }

}