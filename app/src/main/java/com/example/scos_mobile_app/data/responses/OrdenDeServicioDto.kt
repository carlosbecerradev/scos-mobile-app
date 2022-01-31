package com.example.scos_mobile_app.data.responses

data class OrdenDeServicioDto(
    val cliente: ClienteXX?,
    val descripcionDelProblema: String?,
    val empleado: Empleado?,
    val estado: String?,
    val fechaDeAsignacion: String?,
    val fechaDeCancelacion: String?,
    val fechaDeCierre: String?,
    val fechaDeCreacion: String?,
    val fechaDeLlegada: String?,
    val fechaDeResolucion: String?,
    val id: Int?,
    val imagenUrl: String?,
    val motivoDeCancelacion: Any?,
    val revisada: Boolean?,
    val tipoDeIncidencia: String?
)