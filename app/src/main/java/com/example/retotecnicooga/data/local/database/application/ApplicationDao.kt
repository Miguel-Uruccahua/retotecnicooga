package com.example.retotecnicooga.data.local.database.application

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao {
    @Insert
    suspend fun insert(item: ApplicationEntity)

    @Delete
    suspend fun delete(item: ApplicationEntity)

    @Query("Select * from Applicationentity")
    fun getAll(): Flow<List<ApplicationEntity>>

}
