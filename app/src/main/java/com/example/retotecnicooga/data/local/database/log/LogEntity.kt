package com.example.retotecnicooga.data.local.database.log

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.retotecnicooga.domain.model.LogDetail

@Entity
data class LogEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val idApplication : Int,
    val idAppDetail : Int,
    val reference : String,
    val action: LogDetail,
    val date:String
)