package com.example.retotecnicooga.data.di

import com.example.retotecnicooga.data.repository.ActionsRepositoryImpl
import com.example.retotecnicooga.data.repository.DetailsRepositoryImpl
import com.example.retotecnicooga.data.repository.SuggestionsRepositoryImpl
import com.example.retotecnicooga.domain.actions.ActionsRepository
import com.example.retotecnicooga.domain.details.DetailsRepository
import com.example.retotecnicooga.domain.suggestions.SuggestionsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Singleton
    @Binds
    fun bindsActionsRepository(
        actionsRepositoryImpl: ActionsRepositoryImpl
    ):ActionsRepository

    @Singleton
    @Binds
    fun bindsSuggestionsRepository(
        suggestionsRepositoryImpl: SuggestionsRepositoryImpl
    ):SuggestionsRepository

    @Singleton
    @Binds
    fun bindsDetailsRepository(
        detailsRepositoryImpl: DetailsRepositoryImpl
    ):DetailsRepository

}