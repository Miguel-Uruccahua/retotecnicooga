package com.example.retotecnicooga.data.local.database.application

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.retotecnicooga.domain.model.AppDetail
import com.example.retotecnicooga.domain.model.Suggestion
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao {
    @Insert
    suspend fun insert(item: ApplicationEntity):Long

    @Delete
    suspend fun delete(item: ApplicationEntity)

    @Query("Select * from Applicationentity")
    fun getAll(): Flow<List<ApplicationEntity>>

    @Query("Select * from Applicationentity Limit 1")
    suspend fun getOne(): ApplicationEntity?

    @Query("""
           select app.name as appName, app.type as type, '' as detail
            from Applicationentity app 
            where app.minCompatibility = 'Android 8' or app.maxCompatibility = 'Android 8'
            """)
    fun getLowerVersion(): Flow<List<Suggestion>>

    @Query("""
           select app.name as appName, app.type as type, '' as detail
            from Applicationentity app 
            where app.state = 'Desarrollo' 
            """)
    fun getStateDevelopment(): Flow<List<Suggestion>>

    @Query("""
           select app.name as appName, app.type as type, ade.title as detail
            from Applicationentity app 
            LEFT JOIN AppDetailEntity ade on app.id = ade.idApplication
            where ade.priority = 'Critica'
            """)
    fun getHighPriority(): Flow<List<Suggestion>>

    @Query("""
           select app.name as appName, app.type as type, ade.title as detail
            from Applicationentity app 
            LEFT JOIN AppDetailEntity ade on app.id = ade.idApplication
            where ade.assignedTo = ''
            """)
    fun getOffAsigment(): Flow<List<Suggestion>>

    @Query("""
            select app.name as appName, app.type as type, ade.title as detail
            from Applicationentity app 
            LEFT JOIN AppDetailEntity ade on app.id = ade.idApplication
            where ade.state = 'Finalizado' and ade.dateFinish = ''
            """)
    fun getDateEmpty(): Flow<List<Suggestion>>

    @Insert
    fun insertAll(applicationList: List<ApplicationEntity>)


}
