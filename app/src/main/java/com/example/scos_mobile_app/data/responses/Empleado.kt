package com.example.scos_mobile_app.data.responses

data class Empleado(
    val apellidos: String,
    val dni: String,
    val estado: String,
    val fotoUrl: String,
    val id: Int,
    val nombres: String,
    val nroDeCarnet: String,
    val nroDeTelefonoMovil: String,
    val sede: String,
    val sexo: String,
    val tokenDeNotificacion: Any,
    val ubicacion: Any,
    val usuarioId: Int
)