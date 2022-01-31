package com.example.scos_mobile_app.data.responses

data class Ubicacion(
    val direccion: String,
    val fechaDeActualizacion: String,
    val fechaDeCreacion: String,
    val latitud: Double,
    val longitud: Double,
    val ubicacionId: Int
)