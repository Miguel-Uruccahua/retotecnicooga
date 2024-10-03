package com.example.retotecnicooga.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retotecnicooga.domain.actions.ActionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val actionsRepository: ActionsRepository
) : ViewModel() {

    companion object{

    }

    fun insertData(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                actionsRepository.insertData()
            }
        }
    }
}