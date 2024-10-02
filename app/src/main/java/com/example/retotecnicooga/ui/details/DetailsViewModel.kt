package com.example.retotecnicooga.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retotecnicooga.domain.details.DetailsRepository
import com.example.retotecnicooga.domain.model.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    val listLogState: StateFlow<List<Log>> = detailsRepository.getAllLog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), mutableListOf())

}