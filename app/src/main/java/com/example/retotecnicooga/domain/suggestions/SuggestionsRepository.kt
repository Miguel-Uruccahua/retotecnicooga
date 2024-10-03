package com.example.retotecnicooga.domain.suggestions

import com.example.retotecnicooga.domain.model.Suggestion
import kotlinx.coroutines.flow.Flow

interface SuggestionsRepository {

    fun getLowerSuggestions(): Flow<List<Suggestion>>
    fun getPrioritySuggestions(): Flow<List<Suggestion>>
    fun getAssignmentSuggestions(): Flow<List<Suggestion>>
    fun getDateEmptySuggestions(): Flow<List<Suggestion>>

}