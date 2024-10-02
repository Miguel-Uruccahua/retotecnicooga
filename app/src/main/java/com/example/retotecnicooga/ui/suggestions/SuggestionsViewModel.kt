package com.example.retotecnicooga.ui.suggestions

import androidx.lifecycle.ViewModel
import com.example.retotecnicooga.domain.suggestions.SuggestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SuggestionsViewModel @Inject constructor(
    private val suggestionsRepository: SuggestionsRepository
) : ViewModel() {


}