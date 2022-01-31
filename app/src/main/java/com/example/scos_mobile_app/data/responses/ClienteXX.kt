package com.example.scos_mobile_app.data.responses

data class ClienteXX(
    val apellidos: String,
    val direccion: String,
    val dni: String,
    val fechaDeNacimiento: String,
    val id: Int,
    val nombres: String,
    val nroDeTelefonoFijo: String,
    val nroDeTelefonoMovil: String,
    val sede: String,
    val sexo: String,
    val tipoDeServicio: String,
    val tokenDeNotificacion: Any,
    val ubicacion: Ubicacion,
    val usuarioId: Int
)