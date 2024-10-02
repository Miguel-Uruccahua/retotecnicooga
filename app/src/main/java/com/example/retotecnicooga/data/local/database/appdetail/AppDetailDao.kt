package com.example.retotecnicooga.data.local.database.appdetail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppDetailDao {

    @Insert
    suspend fun insert(item: AppDetailEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: AppDetailEntity)

    @Query("Select * from AppDetailEntity where idApplication=:id")
    suspend fun getOneById(id:Int):List<AppDetailEntity>
}