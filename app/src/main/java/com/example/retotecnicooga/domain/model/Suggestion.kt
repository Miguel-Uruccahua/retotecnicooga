package com.example.retotecnicooga.domain.model

data class Suggestion(
    val id : Int,
    val idApplication: Int,
    val name: String,
    val type: String,
    val minCompatibility: String,
    val maxCompatibility: String,
    val state: String,
    val stateApp: String,
    val isUpToolsFollow: Boolean,
    val title: String,
    val description: String,
    val priority: String,
    val assignedTo: String,
    val dateCreated: String,
    val dateFinish: String
){

}
