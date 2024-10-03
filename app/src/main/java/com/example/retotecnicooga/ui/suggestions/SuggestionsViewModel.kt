package com.example.retotecnicooga.ui.suggestions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retotecnicooga.domain.model.Suggestion
import com.example.retotecnicooga.domain.suggestions.SuggestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SuggestionsViewModel @Inject constructor(
    private val suggestionsRepository: SuggestionsRepository
) : ViewModel() {

    val listLowerSuggestion: StateFlow<List<Suggestion>> = suggestionsRepository.getLowerSuggestions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), mutableListOf())

    val listPrioritySuggestion: StateFlow<List<Suggestion>> = suggestionsRepository.getPrioritySuggestions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), mutableListOf())

    val listAssignmentSuggestion: StateFlow<List<Suggestion>> = suggestionsRepository.getAssignmentSuggestions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), mutableListOf())

    val listEmptySuggestion: StateFlow<List<Suggestion>> = suggestionsRepository.getDateEmptySuggestions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), mutableListOf())

}