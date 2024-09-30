package com.example.retotecnicooga.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.retotecnicooga.data.local.database.appdetail.AppDetailDao
import com.example.retotecnicooga.data.local.database.appdetail.AppDetailEntity
import com.example.retotecnicooga.data.local.database.application.ApplicationDao
import com.example.retotecnicooga.data.local.database.application.ApplicationEntity

@Database(
    entities = [
        AppDetailEntity::class,
        ApplicationEntity::class
    ], version = 1
)
abstract class DatabaseApp : RoomDatabase() {
    abstract fun appDetailDao(): AppDetailDao
    abstract fun applicationDao(): ApplicationDao
}
