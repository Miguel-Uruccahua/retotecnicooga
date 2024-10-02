package com.example.retotecnicooga.domain.model


data class Log(
    val id : Int,
    val idApplication : Int,
    val idAppDetail : Int,
    val reference : String,
    val action: LogDetail,
    val date: String,
)

enum class LogDetail(val value: String) {
    ADDAPP("Agregó una nueva Aplicación"),
    ADDAPPDETAIL("Agregó un nuevo evento de Aplicación"),
    DETELEAPP("Eliminó una Aplicación"),
    UPDATEAPPDETAIL("Actualizó una aplicación"),
}
