package com.example.retotecnicooga.domain.model

data class AppDetail(
    val id : Int ,
    val idApplication: Int,
    val title: String,
    val description: String,
    val priority: String,
    val state: String,
    val assignedTo: String,
    val dateCreated: String,
    val dateFinish: String
)