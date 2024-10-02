package com.example.retotecnicooga.ui.actions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retotecnicooga.domain.actions.ActionsRepository
import com.example.retotecnicooga.domain.model.AppDetail
import com.example.retotecnicooga.domain.model.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActionsViewModel @Inject constructor(
    private val actionsRepository: ActionsRepository
) : ViewModel() {

    private val _dataAppDetail = MutableStateFlow<List<AppDetail>>(mutableListOf())
    val dataAppDetail: StateFlow<List<AppDetail>> = _dataAppDetail.asStateFlow()

    fun newApplication(application: Application){
        viewModelScope.launch {
            actionsRepository.addApplication(application)
        }
    }

    fun newAppDetail(appDetail: AppDetail){
        viewModelScope.launch {
            actionsRepository.addAppDetail(appDetail)
        }
    }

    fun updateAppDetail(appDetail: AppDetail){
        viewModelScope.launch {
            actionsRepository.updateAppDetail(appDetail)
        }
    }


    fun deleteApplication(application: Application){
        viewModelScope.launch {
            actionsRepository.deleteApplication(application)
        }
    }

    fun getAppDetail(application: Application) {
        viewModelScope.launch {
            runCatching {
                actionsRepository.getAppDetail(application)
            }.onSuccess{
                _dataAppDetail.emit(it)
            }
        }
    }

    val listState: Flow<List<Application>> = actionsRepository.getApplications()
        .stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000), mutableListOf())



}
