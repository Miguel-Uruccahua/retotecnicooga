package com.example.retotecnicooga.data.repository

import com.example.retotecnicooga.data.local.database.appdetail.AppDetailDao
import com.example.retotecnicooga.data.local.database.application.ApplicationDao
import com.example.retotecnicooga.domain.suggestions.SuggestionsRepository
import javax.inject.Inject

class SuggestionsRepositoryImpl @Inject constructor(
    private val applicationDao: ApplicationDao,
    private val appDetailDao: AppDetailDao
): SuggestionsRepository {

}