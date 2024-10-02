package com.example.retotecnicooga.data.local.database.log

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {

    @Insert
    suspend fun insert(item: LogEntity)

    @Query("Select * from LogEntity")
    fun getAll(): Flow<List<LogEntity>>

}
