package com.example.retotecnicooga.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.retotecnicooga.data.local.database.DatabaseApp
import com.example.retotecnicooga.data.local.database.appdetail.AppDetailDao
import com.example.retotecnicooga.data.local.database.application.ApplicationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideAppDetailDao(databaseApp: DatabaseApp):AppDetailDao{
        return databaseApp.appDetailDao()
    }

    @Provides
    fun provideApplicationDao(databaseApp: DatabaseApp):ApplicationDao{
        return databaseApp.applicationDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): DatabaseApp {
        return Room.databaseBuilder(
            appContext,
            DatabaseApp::class.java,
            "RTOGA"
        ).build()
    }


}

