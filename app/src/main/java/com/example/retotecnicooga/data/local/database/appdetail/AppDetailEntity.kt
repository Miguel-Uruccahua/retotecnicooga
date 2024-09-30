package com.example.retotecnicooga.data.local.database.appdetail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val idApplication: Int,
    val title: String,
    val description: String,
    val priority: String,
    val state: String,
    val assignedTo: String,
    val dateCreated: String,
    val dateFinish: String
)