package com.example.retotecnicooga.data.local.database.application

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ApplicationEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name: String,
    val type: String,
    val minCompatibility: String,
    val maxCompatibility: String,
    val state: String,
    val isUpToolsFollow: Boolean
)