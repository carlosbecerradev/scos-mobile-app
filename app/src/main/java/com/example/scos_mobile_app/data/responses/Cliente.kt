package com.example.scos_mobile_app.data.responses

data class Cliente(
    val id: Long,
    val nombres: String,
    val apellidos: String,
    val fechaDeNacimiento: Any,
    val sexo: String,
    val dni: String,
    val nroDeTelefonoMovil: String,
    val nroDeTelefonoFijo: String,
    val direccion: String,
    val usuarioId: String,
    val tokenDeNotificacion: String,
    val ubicacion: Any,
    val sede: String,
    val tipoDeServicio: String
)
