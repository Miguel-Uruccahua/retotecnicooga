package com.example.retotecnicooga.domain.model

data class Application(
    val id : Int,
    val name: String,
    val type: String,
    val minCompatibility: String,
    val maxCompatibility: String,
    val state: String,
    val isUpToolsFollow: Boolean
)
